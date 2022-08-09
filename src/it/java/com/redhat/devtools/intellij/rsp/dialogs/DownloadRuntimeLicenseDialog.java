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
import com.redhat.devtools.intellij.commonuitest.fixtures.mainidewindow.idestatusbar.IdeStatusBar;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;

import static com.intellij.remoterobot.search.locators.Locators.byXpath;

/**
 * Download server runtime license dialog fixture
 *
 * @author olkornii@redhat.com
 */
@DefaultXpath(by = "MyDialog type", xpath = "//div[@accessiblename='Download Runtime...' and @class='MyDialog']")
@FixtureName(name = "Download runtime license dialog")
public class DownloadRuntimeLicenseDialog extends CommonContainerFixture {
    public DownloadRuntimeLicenseDialog(@NotNull RemoteRobot remoteRobot, @NotNull RemoteComponent remoteComponent) {
        super(remoteRobot, remoteComponent);
    }

    public void agree(RemoteRobot remoteRobot){
        remoteRobot.find(ComponentFixture.class, byXpath("//div[@class='BasicArrowButton']"), Duration.ofSeconds(10)).click();
        remoteRobot.find(ComponentFixture.class, byXpath("//div[@class='JList' and @name='ComboBox.list']"), Duration.ofSeconds(10)).findText("Yes (true)").click();
        button("OK").click();
        final IdeStatusBar ideStatusBar = remoteRobot.find(IdeStatusBar.class);
        ideStatusBar.waitUntilAllBgTasksFinish();
    }
}
