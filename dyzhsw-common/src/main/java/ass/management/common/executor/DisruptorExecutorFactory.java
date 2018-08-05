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

package ass.management.common.executor;

import ass.management.common.concurrent.disruptor.TaskDispatcher;
import ass.management.common.concurrent.disruptor.WaitStrategyType;
import ass.management.common.config.ConfigParameter;
import ass.management.common.utils.SpiMetadata;
import ass.management.common.utils.SystemPropertyUtil;
import org.slf4j.LoggerFactory;

/**
 * Provide a disruptor implementation of executor.
 *
 * jupiter
 * org.jupiter.rpc.executor
 *
 * @author jiachun.fjc
 */
@SpiMetadata(name = ConfigParameter.Executor.DISRUPTOR)
public class DisruptorExecutorFactory extends AbstractExecutorFactory {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(DisruptorExecutorFactory.class);

    @Override
    public CloseableExecutor newExecutor(Target target, String name) {
        final TaskDispatcher executor = new TaskDispatcher(
                coreWorkers(target),
                threadFactory(name),
                queueCapacity(target),
                maxWorkers(target),
                waitStrategyType(target, WaitStrategyType.LITE_BLOCKING_WAIT),
                "disruptor");

        return new CloseableExecutor() {

            @Override
            public void execute(Runnable r) {
                executor.execute(r);
            }

            @Override
            public void shutdown() {
                logger.warn("DisruptorExecutorFactory#{} shutdown.", executor);
                executor.shutdown();
            }
        };
    }

    private WaitStrategyType waitStrategyType(Target target, WaitStrategyType defaultType) {
        WaitStrategyType strategyType = null;
        switch (target) {
            case DEFAULT:
                strategyType = WaitStrategyType.parse(SystemPropertyUtil.get(DEFAULT_DISRUPTOR_WAIT_STRATEGY_TYPE));
                break;
        }

        return strategyType == null ? defaultType : strategyType;
    }
}
