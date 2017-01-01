package com.colantoni.federico.networklibrary.lint.registry;


import com.android.tools.lint.detector.api.Issue;
import com.colantoni.federico.networklibrary.lint.detector.NetworkConnectionLintDetector;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class NetworkConnectionLintRegistryTest {

    private NetworkConnectionLintRegistry mNetworkConnectionLintRegistry;

    @Before
    public void setUp() throws Exception {

        mNetworkConnectionLintRegistry = new NetworkConnectionLintRegistry();
    }

    @Test
    public void testNumberOfIssues() throws Exception {

        int size = mNetworkConnectionLintRegistry.getIssues().size();

        assertThat(size).isEqualTo(1);
    }

    @Test
    public void testGetIssues() throws Exception {

        List<Issue> actual = mNetworkConnectionLintRegistry.getIssues();
        assertThat(actual).contains(NetworkConnectionLintDetector.ISSUE);
    }
}
