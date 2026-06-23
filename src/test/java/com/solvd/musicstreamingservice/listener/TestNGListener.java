package com.solvd.musicstreamingservice.listener;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener implements ITestListener, ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        System.out.println("On suite start: " + suite.getName());
    }

    @Override
    public void onFinish(ISuite suite) {
        System.out.println("On suite finish   : " + suite.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("On test start: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("On test success: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("On test failure: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("On test skipped: " + result.getName());
    }
}