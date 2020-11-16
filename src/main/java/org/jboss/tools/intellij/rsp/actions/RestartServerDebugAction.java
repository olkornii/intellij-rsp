/*******************************************************************************
 * Copyright (c) 2020 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v2.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 * Red Hat, Inc. - initial API and implementation
 ******************************************************************************/
package org.jboss.tools.intellij.rsp.actions;

import com.intellij.openapi.project.Project;
import org.jboss.tools.intellij.rsp.client.IntelliJRspClientLauncher;
import org.jboss.tools.intellij.rsp.ui.tree.RspTreeModel;

public class RestartServerDebugAction extends RestartServerAction {
    protected void startServer(RspTreeModel.ServerStateWrapper sel, Project project, IntelliJRspClientLauncher client) {
        StartServerDebugAction.startServerDebugModeInternal(sel, project, client);
    }
}
