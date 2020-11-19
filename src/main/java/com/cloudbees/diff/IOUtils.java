package com.cloudbees.diff;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

class IOUtils {
    public static void closeQuietly(Closeable thing) {
        try {
            if (thing != null) thing.close();
        } catch (IOException e) {
            //Not important
        }
    }

    public static void copy(OutputStream out, InputStream in) throws IOException {
        byte[] buffer = new byte[4096];

        int n;
        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
    }

    public static void copyStreamsCloseAll(OutputStream out, InputStream in) throws IOException {
        copy(out, in);
        out.close();
        in.close();
    }

    public static void copyStreamsCloseAll(Writer writer, Reader reader) throws IOException {
        char[] buffer = new char[4096];

        int n;
        while ((n = reader.read(buffer)) != -1) {
            writer.write(buffer, 0, n);
        }

        writer.close();
        reader.close();
    }
}