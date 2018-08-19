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

package ass.management.common.concurrent.pool.threadfactory;

import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Named thread factory.
 *
 * jupiter
 * org.jupiter.common.util
 *
 * @author jiachun.fjc
 */
public class CustomThreadFactory implements ThreadFactory {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CustomThreadFactory.class);


    private AtomicInteger count = new AtomicInteger(0);
    private String customThreadFactoryName;
    public CustomThreadFactory(String customThreadFactoryName) {
        this.customThreadFactoryName = customThreadFactoryName;
    }
    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        String threadName = CustomThreadFactory.class.getSimpleName() + ":" + customThreadFactoryName + ":" + count.addAndGet(1);
        logger.info(threadName);
        t.setName(threadName);
        return t;
    }
}
