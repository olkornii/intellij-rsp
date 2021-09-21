/*******************************************************************************
 * Copyright (c) 2021 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v2.0 which accompanies this distribution,
 * and is available at https://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 * Red Hat, Inc. - initial API and implementation
 ******************************************************************************/
package com.redhat.devtools.intellij.rsp.dialogs;

import com.intellij.remoterobot.RemoteRobot;
import com.intellij.remoterobot.data.RemoteComponent;
import com.intellij.remoterobot.fixtures.CommonContainerFixture;
import com.intellij.remoterobot.fixtures.ComponentFixture;
import com.intellij.remoterobot.fixtures.DefaultXpath;
import com.intellij.remoterobot.fixtures.FixtureName;
import com.intellij.remoterobot.fixtures.JListFixture;
import com.intellij.remoterobot.fixtures.JPopupMenuFixture;
import com.intellij.remoterobot.fixtures.dataExtractor.RemoteText;
import com.intellij.remoterobot.utils.Keyboard;
import com.intellij.remoterobot.utils.WaitForConditionTimeoutException;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import static com.intellij.remoterobot.search.locators.Locators.byXpath;
import static com.intellij.remoterobot.utils.RepeatUtilsKt.waitFor;
import static com.redhat.devtools.intellij.commonUiTestLibrary.utils.labels.ButtonLabels.nextLabel;
import static com.redhat.devtools.intellij.rsp.tests.AbstractRspServersTest.isRspServerStarted;

/**
 * Download server runtime dialog fixture
 *
 * @author olkornii@redhat.com
 */
@DefaultXpath(by = "MyDialog type", xpath = "//div[@accessiblename='Download Server Runtime...' and @class='MyDialog']")
@FixtureName(name = "Download server runtime")
public class DownloadServerRuntimeDialog extends CommonContainerFixture {
    public DownloadServerRuntimeDialog(@NotNull RemoteRobot remoteRobot, @NotNull RemoteComponent remoteComponent) {
        super(remoteRobot, remoteComponent);
    }

    /**
     * Select the server for download by name
     *
     * @param remoteRobot reference to the RemoteRobot instance
     * @param serverName server name to select
     */
    public void selectServer(RemoteRobot remoteRobot, String serverName){
        ComponentFixture serversListFixture = remoteRobot.find(ComponentFixture.class, byXpath("//div[@class='JBList']"), Duration.ofSeconds(10));
        serversListFixture.findAllText().get(serversListFixture.findAllText().size() - 1).click();
        waitFor(Duration.ofSeconds(30), Duration.ofSeconds(1), "Server did not found.", () -> selectServerInList(remoteRobot, serversListFixture , serverName));
        button("OK").click();
        DownloadRuntimeLicenseDialog licenseAgreeDialog = remoteRobot.find(DownloadRuntimeLicenseDialog.class, Duration.ofSeconds(10));
        licenseAgreeDialog.agree(remoteRobot);
    }

    private boolean selectServerInList(RemoteRobot remoteRobot, ComponentFixture serversListFixture, String serverName) {
        try {
            serversListFixture.findText(serverName).click();
            return true;
        } catch (NoSuchElementException e){
            Keyboard myKeyboard = new Keyboard(remoteRobot);
            myKeyboard.down();
            return false;
        }
    }
}
