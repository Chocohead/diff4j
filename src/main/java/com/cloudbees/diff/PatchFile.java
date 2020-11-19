/*
 * Copyright 2020 Chocohead
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * 
 * It is also additionally distributed under LGPL 2.1 for the purposes
 * of being included within Diff4J
 */
package com.cloudbees.diff;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;

public abstract class PatchFile {
    public static PatchFile from(final String string) {
        return new PatchFile() {
            @Override
            public Reader openReader() throws IOException {
                return new StringReader(string);
            }
        };
    }

    public static PatchFile from(byte[] data) {
        return from(data, null);
    }

    public static PatchFile from(final byte[] data, Charset encoding) {
        return new EncodedPatchFile(encoding) {
            @Override
            protected InputStream openStream() throws IOException {
                return new ByteArrayInputStream(data);
            }
        };
    }

    public static PatchFile from(File file) {
        return from(file, null);
    }

    public static PatchFile from(final File file, Charset encoding) {
        return new EncodedPatchFile(encoding) {
            @Override
            protected InputStream openStream() throws IOException {
                return new FileInputStream(file);
            }
        };
    }

    public abstract Reader openReader() throws IOException;
}