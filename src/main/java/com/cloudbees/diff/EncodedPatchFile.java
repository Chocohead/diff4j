package com.cloudbees.diff;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

public abstract class EncodedPatchFile extends PatchFile {
	/**
     * Encoding of the patch file.
     *
     * Null to auto-sniff, based on the {@link ContextualPatch#MAGIC} headerline.
     * This is a carry-over from the original source code that came from NetBeans.
     */
    private Charset encoding;

    public EncodedPatchFile() {
        this(null);
    }

    public EncodedPatchFile(Charset encoding) {
        this.encoding = encoding;
    }

    protected abstract InputStream openStream() throws IOException;

    @Override
    public Reader openReader() throws IOException {
        Charset encoding = this.encoding;

        if (encoding == null) {
            /*
                Auto-sniffing behaviour:
    
                1. look for the MAGIC line in the beginning. If that's present, the file is in UTF-8
                2. otherwise diff file spec doesn't come with the specification of encoding, so the best
                   you can do is to use the platform encoding, which I think most closely resembles
                   the original diff tool's behaviour.
             */
            int read;
            byte[] buffer = new byte[ContextualPatch.MAGIC.length()];

            InputStream in = openStream();
            try {
                read = in.read(buffer);
            } finally {
                IOUtils.closeQuietly(in);
            }

            Charset UTF8 = Charset.forName("UTF-8");
            if (read != -1 && ContextualPatch.MAGIC.equals(new String(buffer, UTF8))) {
                this.encoding = encoding = UTF8;
            } else {
                this.encoding = encoding = Charset.defaultCharset();
            }
        }

        return new InputStreamReader(openStream(), encoding);
    }
}