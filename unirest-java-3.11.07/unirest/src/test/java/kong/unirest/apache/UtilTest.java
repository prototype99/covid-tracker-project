/**
 * The MIT License
 *
 * Copyright for portions of unirest-java are held by Kong Inc (c) 2013.
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package kong.unirest.apache;

import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.nio.client.HttpAsyncClient;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    @Test
    void canCast() {
        Object foo = new Foo(){};

        Optional<Foo> fooCast = Util.tryCast(foo, Foo.class);
        assertTrue(fooCast.isPresent());
        assertEquals(foo, fooCast.get());
        assertFalse(Util.tryCast("foo", Foo.class).isPresent());
        assertFalse(Util.tryCast(null, Foo.class).isPresent());
        assertTrue(Util.tryCast(new Bar(), Foo.class).isPresent());
    }

    @Test
    void canCastAAsyncClient() {
        HttpAsyncClient build = HttpAsyncClientBuilder.create().build();

        assertTrue(Util.tryCast(build, CloseableHttpAsyncClient.class).isPresent());
    }

    public abstract static class Foo {}

    public class Bar extends Foo {}
}