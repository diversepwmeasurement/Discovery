package com.nepxion.discovery.plugin.strategy.starter.agent.plugin.discovery.service;

/**
 * <p>Title: Nepxion Discovery</p>
 * <p>Description: Nepxion Discovery</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 *
 * @author zifeihan
 * @version 1.0
 */

import com.nepxion.discovery.plugin.strategy.starter.agent.core.callback.TransformCallback;
import com.nepxion.discovery.plugin.strategy.starter.agent.core.callback.TransformTemplate;
import com.nepxion.discovery.plugin.strategy.starter.agent.core.logger.AgentLogger;
import com.nepxion.discovery.plugin.strategy.starter.agent.core.matcher.ClassMatcher;
import com.nepxion.discovery.plugin.strategy.starter.agent.core.matcher.MatcherFactory;
import com.nepxion.discovery.plugin.strategy.starter.agent.core.plugin.Plugin;
import com.nepxion.discovery.plugin.strategy.starter.agent.core.threadlocal.ThreadLocalCopier;

import java.security.ProtectionDomain;

public class DiscoveryServicePlugin extends Plugin {
    private static final AgentLogger LOG = AgentLogger.getLogger(DiscoveryServicePlugin.class.getName());

    @Override
    public void install(TransformTemplate transformTemplate) {
        ClassMatcher classMatcher = MatcherFactory.newClassNameMatcher("com.nepxion.discovery.plugin.strategy.service.context.RestStrategyContext");
        transformTemplate.transform(classMatcher, new TransformCallback() {
            @Override
            public byte[] doInTransform(ClassLoader classLoader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
                ThreadLocalCopier.register(new RestStrategyContextHook());

                return null;
            }
        });
    }
}