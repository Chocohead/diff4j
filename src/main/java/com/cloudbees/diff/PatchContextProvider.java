/*
 * ForgeGradle
 * Copyright (C) 2018 Forge Development LLC
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301
 * USA
 */
package com.cloudbees.diff;

import com.cloudbees.diff.ContextualPatch.SinglePatch;

import java.io.IOException;
import java.util.List;

public interface PatchContextProvider {
    List<String> getData(SinglePatch patch) throws IOException;

    void setData(SinglePatch patch, List<String> data) throws IOException;

    void setFailed(SinglePatch patch, List<String> lines) throws IOException;
}
