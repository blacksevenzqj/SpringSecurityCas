/*
 * Copyright (c) 2015 The Jupiter Project
 *
 * Licensed under the Apache License, version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ass.management.common.concurrent.executor;

import ass.management.common.concurrent.pool.threadfactory.CustomThreadFactory;
import ass.management.common.utils.JConstants;
import ass.management.common.utils.SystemPropertyUtil;

import java.util.concurrent.ThreadFactory;

/**
 * jupiter
 * org.jupiter.rpc.executor
 *
 * @author jiachun.fjc
 */
public abstract class AbstractExecutorFactory implements ExecutorFactory {

    protected ThreadFactory threadFactory(String name) {
        return new CustomThreadFactory(name);
    }

    protected int coreWorkers(Target target) {
        switch (target) {
            case DEFAULT:
                return SystemPropertyUtil.getInt(DEFAULT_EXECUTOR_CORE_WORKERS, JConstants.AVAILABLE_PROCESSORS << 1);
            default:
                throw new IllegalArgumentException(String.valueOf(target));
        }
    }

    protected int maxWorkers(Target target) {
        switch (target) {
            case DEFAULT:
                return SystemPropertyUtil.getInt(DEFAULT_EXECUTOR_MAX_WORKERS, 32);
            default:
                throw new IllegalArgumentException(String.valueOf(target));
        }
    }

    protected int queueCapacity(Target target) {
        switch (target) {
            case DEFAULT:
                return SystemPropertyUtil.getInt(DEFAULT_EXECUTOR_QUEUE_CAPACITY, 32768);
            default:
                throw new IllegalArgumentException(String.valueOf(target));
        }
    }
}
