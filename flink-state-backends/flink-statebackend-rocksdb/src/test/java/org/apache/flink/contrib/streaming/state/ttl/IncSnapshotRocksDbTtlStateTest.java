/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.flink.contrib.streaming.state.ttl;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.contrib.streaming.state.RocksDBConfigurableOptions;
import org.apache.flink.contrib.streaming.state.RocksDBStateBackend;
import org.apache.flink.contrib.streaming.state.writer.WriteBatchMechanism;
import org.apache.flink.runtime.state.StateBackend;
import org.apache.flink.util.TernaryBoolean;

/** Test suite for rocksdb state TTL with incremental snapshot strategy. */
public class IncSnapshotRocksDbTtlStateTest extends RocksDBTtlStateTestBase {
    @Override
    StateBackend createStateBackend() {
        RocksDBStateBackend backend = createStateBackend(TernaryBoolean.TRUE);
        Configuration config = new Configuration();
        config.set(
                RocksDBConfigurableOptions.WRITE_BATCH_MECHANISM, WriteBatchMechanism.WRITE_BATCH);
        backend = backend.configure(config, Thread.currentThread().getContextClassLoader());
        return backend;
    }

    @Override
    public boolean fullSnapshot() {
        return false;
    }
}
