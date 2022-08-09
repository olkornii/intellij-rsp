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
package com.redhat.devtools.intellij.rsp.tests;

import com.intellij.remoterobot.RemoteRobot;
import com.intellij.remoterobot.fixtures.ComponentFixture;
import com.intellij.remoterobot.fixtures.JPopupMenuFixture;
import com.redhat.devtools.intellij.commonuitest.fixtures.mainidewindow.idestatusbar.IdeStatusBar;
import com.redhat.devtools.intellij.rsp.dialogs.DeleteServerDialog;
import com.redhat.devtools.intellij.rsp.dialogs.DownloadServerRuntimeDialog;
import org.assertj.swing.core.MouseButton;

import java.lang.reflect.Array;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static com.intellij.remoterobot.utils.RepeatUtilsKt.waitFor;
import static com.redhat.devtools.intellij.rsp.tests.AbstractRspServersTest.isRspServerStarted;

/**
 * @author olkornii@redhat.com
 */
public class DownloadAndRunCommunityServersTest {

//    private static final List<String> serversForDownload = Arrays.asList("Apache Felix 7.0.1",
//            "Apache Karaf 4.3.2", "Apache Tomcat 10.0.8", "Payara 5.2020.7", "WebSphere Liberty 21.0.0.1");

    private static final List<String> serversForDownload = Arrays.asList("Apache Tomcat 10.0.8");

    public static void downloadAndRunCommunityServers(RemoteRobot robot, ComponentFixture rspViewTree){
        for (String serverName : serversForDownload) {
            rspViewTree.findAllText().get(0).click(MouseButton.RIGHT_BUTTON);
            JPopupMenuFixture contextMenu = robot.find(JPopupMenuFixture.class, JPopupMenuFixture.Companion.byType(), Duration.ofSeconds(10));
            contextMenu.select("Download Server");
            DownloadServerRuntimeDialog downloadServerDialog = robot.find(DownloadServerRuntimeDialog.class, Duration.ofSeconds(10));
            downloadServerDialog.selectServer(robot, serverName);
            if (rspViewTree.findAllText().size() == 2){
                rspViewTree.findAllText().get(0).doubleClick();
            }
            rspViewTree.findAllText().get(1).click(MouseButton.RIGHT_BUTTON);
            contextMenu = robot.find(JPopupMenuFixture.class, JPopupMenuFixture.Companion.byType(), Duration.ofSeconds(10));
            contextMenu.select("Start Server (Run)");
            waitFor(Duration.ofSeconds(300), Duration.ofSeconds(1), "Server did not started.", () -> isRspServerStarted(rspViewTree ,1));
            rspViewTree.findAllText().get(1).click(MouseButton.RIGHT_BUTTON);
            contextMenu = robot.find(JPopupMenuFixture.class, JPopupMenuFixture.Companion.byType(), Duration.ofSeconds(10));
            contextMenu.select("Delete Server");
            DeleteServerDialog deleteDialog = robot.find(DeleteServerDialog.class, Duration.ofSeconds(10));
            deleteDialog.ok();
        }
    }
}
