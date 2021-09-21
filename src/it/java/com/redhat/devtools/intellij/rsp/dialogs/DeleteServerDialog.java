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
import com.intellij.remoterobot.fixtures.DefaultXpath;
import com.intellij.remoterobot.fixtures.FixtureName;
import org.jetbrains.annotations.NotNull;

/**
 * Download server runtime license dialog fixture
 *
 * @author olkornii@redhat.com
 */
@DefaultXpath(by = "MyDialog type", xpath = "//div[@accessiblename='Delete Server?' and @class='MyDialog']")
@FixtureName(name = "Delete server dialog")
public class DeleteServerDialog extends CommonContainerFixture {
    public DeleteServerDialog(@NotNull RemoteRobot remoteRobot, @NotNull RemoteComponent remoteComponent) {
        super(remoteRobot, remoteComponent);
    }

    public void ok(){
        button("OK").click();
    }
}