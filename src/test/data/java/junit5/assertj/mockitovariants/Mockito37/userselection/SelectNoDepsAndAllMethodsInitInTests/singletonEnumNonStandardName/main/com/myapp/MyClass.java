/*
 * Copyright 2026 Squaretest LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.myapp;

import org.apache.commons.lang3.mutable.MutableInt;
import org.apache.commons.lang3.mutable.MutableObject;

import javax.net.SocketFactory;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public enum MyClass {

    ONLYVALUE(Executors.newSingleThreadExecutor(), null, 10L);

    private final ExecutorService mExecutorService;
    private final SocketFactory mSocketFactory;
    private final long mTimeout;

    MyClass(final ExecutorService executorService, final SocketFactory socketFactory, final long timeout) {
        mExecutorService = executorService;
        mSocketFactory = socketFactory;
        mTimeout = timeout;
    }

    // Methods with return type and no args.
    public Socket createNewConnection() throws IOException {
        return mSocketFactory.createSocket();
    }

    public Socket tryCreateNewConnection() throws RuntimeException {
        try {
            return mSocketFactory.createSocket();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Socket safeCreateNewConnection() {
        try {
            return mSocketFactory.createSocket();
        } catch (IOException e) {
            return null;
        }
    }

    // Methods with return type and args.
    public Socket createNewConnection(final String host, final int port) throws IOException {
        return mSocketFactory.createSocket();
    }

    public Socket tryCreateNewConnection(final String host, final int port) throws RuntimeException {
        try {
            return mSocketFactory.createSocket();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Socket safeCreateNewConnection(final String host, final int port) {
        try {
            return mSocketFactory.createSocket();
        } catch (IOException e) {
            return null;
        }
    }

    // Methods with return type and non-simple args.
    public Socket createNewConnection(final String host, final MutableInt port) throws IOException {
        return mSocketFactory.createSocket();
    }

    public Socket tryCreateNewConnection(final String host, final MutableInt port) throws RuntimeException {
        try {
            return mSocketFactory.createSocket();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Socket safeCreateNewConnection(final String host, final MutableInt port) {
        try {
            return mSocketFactory.createSocket();
        } catch (IOException e) {
            return null;
        }
    }

    // Methods with no return type and complex args.
    public void createNewConnection(final String host, final MutableObject<Socket> outSocket) throws IOException {
        outSocket.setValue(mSocketFactory.createSocket());
    }

    public void tryCreateNewConnection(final String host,final MutableObject<Socket> outSocket) throws RuntimeException {
        try {
            outSocket.setValue(mSocketFactory.createSocket());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void safeCreateNewConnection(final String host, final MutableObject<Socket> outSocket) {
        try {
            outSocket.setValue(mSocketFactory.createSocket());
        } catch (IOException e) {
            outSocket.setValue(null);
        }
    }

    // no args and void return type.
    public void closeAllConnections() throws IOException {
        System.out.println("");
    }

    public void safeCloseAllConnections() {
        System.out.println("");
    }

    // complex args and void return type.
    public void closeAllConnections(List<MutableObject<Socket>> socketsToClose) throws IOException {
        System.out.println(socketsToClose);
    }

    public void safeCloseAllConnections(List<MutableObject<Socket>> socketsToClose) {
        System.out.println(socketsToClose);
    }

    // Simple args, no return type.
    public void closeConnections(String host) throws IOException {
        System.out.println(host);
    }

    public void safeCloseConnections(String host) {
        System.out.println(host);
    }
}
