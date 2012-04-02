/**
 * Copyright (c) 2011-2012, Qulice.com
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met: 1) Redistributions of source code must retain the above
 * copyright notice, this list of conditions and the following
 * disclaimer. 2) Redistributions in binary form must reproduce the above
 * copyright notice, this list of conditions and the following
 * disclaimer in the documentation and/or other materials provided
 * with the distribution. 3) Neither the name of the Qulice.com nor
 * the names of its contributors may be used to endorse or promote
 * products derived from this software without specific prior written
 * permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT
 * NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
 * THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.qulice.pmd;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import net.sourceforge.pmd.DataSource;

/**
 * Bridge between <code>DataSource</code> and <code>Reader</code>.
 *
 * @author Dmitry Bashkin (dmitry.bashkin@qulice.com)
 * @version $Id: SourceValidator.java 297 2011-11-13 14:01:00Z guard $
 */
public final class DataSourceReader {

    /**
     * Data source.
     */
    private final transient DataSource source;

    /**
     * Creates new instance of <code>DataSourceReader</code> with the specified
     * <code>DataSource</code>.
     * @param src Data source to work with.
     */
    public DataSourceReader(final DataSource src) {
        this.source = src;
    }

    /**
     * Returns buffered reader.
     * @return Buffered reader.
     */
    public Reader getReader() {
        InputStream input = null;
        try {
            input = this.source.getInputStream();
        } catch (java.io.IOException exception) {
            throw new IllegalArgumentException(exception);
        }
        Reader reader = null;
        try {
            reader = new InputStreamReader(input, "UTF8");
        } catch (UnsupportedEncodingException exception) {
            throw new IllegalArgumentException(exception);
        }
        return new BufferedReader(reader);
    }
}