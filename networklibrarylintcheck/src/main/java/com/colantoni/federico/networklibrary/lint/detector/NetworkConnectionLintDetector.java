package com.colantoni.federico.networklibrary.lint.detector;


import com.android.SdkConstants;
import com.android.annotations.NonNull;
import com.android.annotations.Nullable;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.ClassContext;
import com.android.tools.lint.detector.api.Context;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.Location;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.android.tools.lint.detector.api.Speed;
import com.android.tools.lint.detector.api.TextFormat;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.ListIterator;


public class NetworkConnectionLintDetector extends Detector implements Detector.UastScanner {

    private static final String ON_CREATE_SIG = "(Landroid/os/Bundle;)V";

    private static final String BASE_URL_METHOD = "NetworkConnection.setBaseUrl";

    private static final Class<? extends Detector> DETECTOR_CLASS = NetworkConnectionLintDetector.class;

    private static final EnumSet<Scope> DETECTOR_SCOPE = Scope.JAVA_FILE_SCOPE;

    private static final Implementation IMPLEMENTATION = new Implementation(DETECTOR_CLASS, DETECTOR_SCOPE);

    private static final String ISSUE_ID = "NetworkConnection";

    private static final String ISSUE_DESCRIPTION = "NetworkConnection.setBaseUrl() method required";

    private static final String ISSUE_EXPLANATION = "You have to set the base URL for your services using the NetworkConnection.setBaseUrl() method, otherwise project won't compile. It's " +
            "recommended to call this method inside a custom " + "application class.";

    private static final Category ISSUE_CATEGORY = Category.CORRECTNESS;

    private static final int ISSUE_PRIORITY = 8;

    private static final Severity ISSUE_SEVERITY = Severity.FATAL;

    public static final Issue ISSUE = Issue.create(ISSUE_ID, ISSUE_DESCRIPTION, ISSUE_EXPLANATION, ISSUE_CATEGORY, ISSUE_PRIORITY, ISSUE_SEVERITY, IMPLEMENTATION);

    /**
     * Constructs a new {@link NetworkConnectionLintDetector} check
     */
    public NetworkConnectionLintDetector() {
        //Empty detector constructor
    }

    @Override
    public void afterCheckProject(@NotNull final Context context) {
        if (context.getProject() == context.getMainProject() && !context.getMainProject().isLibrary()) {
            context.report(ISSUE, Location.create(context.file), ISSUE.getBriefDescription(TextFormat.TEXT));
        }
    }

    @NotNull
    @Override
    public Speed getSpeed(@NotNull Issue issue) {
        return super.getSpeed(issue);
    }

    @Override
    public void checkClass(@NotNull final ClassContext context, @NotNull final ClassNode classNode) {
        super.checkClass(context, classNode);
        Location location = context.getLocation(classNode);
        if (!context.getDriver().isSubclassOf(classNode, SdkConstants.CLASS_ACTIVITY) || !context.getDriver().isSubclassOf(classNode, SdkConstants.CLASS_FRAGMENT) || !context.getDriver()
                .isSubclassOf(classNode, SdkConstants.CLASS_V4_FRAGMENT.defaultName()) || !context.getDriver().isSubclassOf(classNode, SdkConstants.CLASS_APPLICATION)) {
            return;
        }
        boolean callsBeginTransaction = checkIfCallBeginTransation(classNode);
        if (!callsBeginTransaction) {
            context.report(ISSUE, location, "You have to call " + BASE_URL_METHOD);
        }
    }
    //    @Override
    //    public void checkClass(@NonNull final JavaContext context, @Nullable final ClassDeclaration declaration, @NonNull final Node node, @NonNull final JavaParser.ResolvedClass resolvedClass) {
    //
    //        if (node == null) {
    //
    //            return;
    //        }
    //
    //        JavaParser.ResolvedClass superClass = resolvedClass.getSuperClass();
    //
    //        if (superClass.getSimpleName().contains(SdkConstants.CLASS_ACTIVITY) || superClass.getSimpleName().contains(SdkConstants.CLASS_APPLICATION)) {
    //
    //            //we are in activity class
    //
    //            iterateOnClassMethods(resolvedClass, "onCreate");
    //        } else {
    //
    //            //we are in fragment
    //
    //            iterateOnClassMethods(resolvedClass, "onCreateView");
    //        }
    //    }

    @Override
    public List<String> applicableSuperClasses() {
        return Arrays.asList(SdkConstants.CLASS_ACTIVITY, SdkConstants.CLASS_APPLICATION, SdkConstants.CLASS_FRAGMENT, SdkConstants.CLASS_V4_FRAGMENT.defaultName());
    }
    //    private static void iterateOnClassMethods(@NonNull final JavaParser.ResolvedClass resolvedClass, final String methodName) {
    //
    //        Iterable<JavaParser.ResolvedMethod> methods = resolvedClass.getMethods(methodName, false);
    //
    //        for (JavaParser.ResolvedMethod m : methods) {
    //
    //
    //        }
    //    }

    @SuppressWarnings("unchecked") // ASM API
    private boolean checkIfCallBeginTransation(final ClassNode classNode) {
        MethodNode onCreate = findMethod(classNode.methods, "onCreate", ON_CREATE_SIG);
        assert onCreate != null;
        return checkIfCallBeginTransationAux(classNode, onCreate);
    }

    @SuppressWarnings("unchecked") // ASM API
    private boolean checkIfCallBeginTransationAux(final ClassNode classNode, @NonNull final MethodNode method) {
        ListIterator<AbstractInsnNode> iterator = method.instructions.iterator();
        while (iterator.hasNext()) {
            AbstractInsnNode insnNode = iterator.next();
            if (insnNode.getType() == AbstractInsnNode.METHOD_INSN) {
                MethodInsnNode methodInsnNode = (MethodInsnNode) insnNode;
                if ("setBaseUrl".equals(methodInsnNode.name) && "NetworkConnection".equals(methodInsnNode.owner)) {
                    return true;
                }
                if (methodInsnNode.owner.equals(classNode.name)) {
                    MethodNode child = findMethod(classNode.methods, methodInsnNode.name, methodInsnNode.desc);
                    if (child != null) {
                        return checkIfCallBeginTransationAux(classNode, child);
                    }
                }
            }
        }
        return false;
    }

    @Nullable
    private MethodNode findMethod(@NonNull final List<MethodNode> methods, @NonNull final String name, @NonNull final String desc) {
        for (MethodNode method : methods) {
            if (name.equals(method.name) && desc.equals(method.desc)) {
                return method;
            }
        }
        return null;
    }
}
