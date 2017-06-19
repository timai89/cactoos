/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Yegor Bugayenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.cactoos.func;

import java.util.Iterator;
import org.cactoos.Scalar;

/**
 * Logical conjunction.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 0.8
 */
public final class LogConj implements Scalar<Boolean> {

    /**
     * The iterator.
     */
    private final Iterable<Scalar<Boolean>> iterable;

    /**
     * Ctor.
     * @param iterable The iterable
     */
    public LogConj(final Iterable<Scalar<Boolean>> iterable) {
        this.iterable = iterable;
    }

    @Override
    public Boolean asValue() throws Exception {
        final Iterator<Scalar<Boolean>> iterator = this.iterable.iterator();
        return this.conjunction(iterator, true);
    }

    /**
     * Conjunction.
     *
     * @param iterator The iterator
     * @param value Previous value
     * @return The result
     * @throws Exception If fails
     */
    private Boolean conjunction(
        final Iterator<Scalar<Boolean>> iterator,
        final boolean value
    ) throws Exception {
        final Boolean result;
        if (iterator.hasNext() && value) {
            result = this.conjunction(iterator, iterator.next().asValue());
        } else {
            result = value;
        }
        return result;
    }
}
