package com.colantoni.federico.networklibrary.lint.detector;


import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Context;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Location;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.android.tools.lint.detector.api.TextFormat;

import java.io.File;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import lombok.ast.AstVisitor;
import lombok.ast.ClassDeclaration;
import lombok.ast.ClassLiteral;
import lombok.ast.ForwardingAstVisitor;
import lombok.ast.Node;


public class NetworkConnectionLintDetector extends Detector implements Detector.JavaScanner {

    private static final Class<? extends Detector> DETECTOR_CLASS = NetworkConnectionLintDetector.class;

    private static final EnumSet<Scope> DETECTOR_SCOPE = Scope.JAVA_FILE_SCOPE;

    private static final Implementation IMPLEMENTATION = new Implementation(DETECTOR_CLASS, DETECTOR_SCOPE);

    private static final String ISSUE_ID = "NetworkConnection";

    private static final String ISSUE_DESCRIPTION = "NetworkConnection.setBaseUrl() method required";

    private static final String ISSUE_EXPLANATION =
            "You have to set the base URL for your services using the NetworkConnection.setBaseUrl() method, otherwise project won't compile. It's recommended to call this method inside a custom " +
            "application class.";

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
    public boolean appliesTo(final Context context, final File file) {

        return true;
    }

    @Override
    public AstVisitor createJavaVisitor(final JavaContext context) {

        return new NetworkConnectionClassDeclarationChecker(context);
    }

    @Override
    public List<Class<? extends Node>> getApplicableNodeTypes() {

        return Arrays.<Class<? extends Node>>asList(ClassDeclaration.class, ClassLiteral.class);
    }


    private static class NetworkConnectionClassDeclarationChecker extends ForwardingAstVisitor {

        private JavaContext mContext;

        public NetworkConnectionClassDeclarationChecker(final JavaContext javaContext) {

            this.mContext = javaContext;
        }

        @Override
        public boolean visitClassDeclaration(final ClassDeclaration node) {

            mContext.report(ISSUE, Location.create(mContext.file), ISSUE.getBriefDescription(TextFormat.TEXT));

            return super.visitClassDeclaration(node);
        }
    }
}
