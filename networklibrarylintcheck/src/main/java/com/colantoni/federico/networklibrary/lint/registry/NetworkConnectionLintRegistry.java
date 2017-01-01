package com.colantoni.federico.networklibrary.lint.registry;


import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.Issue;
import com.colantoni.federico.networklibrary.lint.detector.NetworkConnectionLintDetector;

import java.util.Arrays;
import java.util.List;


public class NetworkConnectionLintRegistry extends IssueRegistry {

    private List<Issue> mIssues = Arrays.asList(NetworkConnectionLintDetector.ISSUE);

    @Override
    public List<Issue> getIssues() {

        return mIssues;
    }
}
