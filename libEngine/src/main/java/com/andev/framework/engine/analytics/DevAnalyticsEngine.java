package com.andev.framework.engine.analytics;

import java.util.Map;

import com.andev.framework.engine.DevEngineAssist;
import com.andev.framework.engine.analytics.IAnalyticsEngine.EngineConfig;
import com.andev.framework.engine.analytics.IAnalyticsEngine.EngineItem;

/**
 * detail: Analytics Engine
 *
 * @author Ttt
 */
public final class DevAnalyticsEngine {

    private DevAnalyticsEngine() {
    }

    private static final DevEngineAssist<IAnalyticsEngine<? super IAnalyticsEngine.EngineConfig, ? super EngineItem>> sAssist = new DevEngineAssist<>();

    /**
     * 获取 Engine
     *
     * @return {@link IAnalyticsEngine}
     */
    public static IAnalyticsEngine<? super EngineConfig, ? super EngineItem> getEngine() {
        return sAssist.getEngine();
    }

    /**
     * 获取 Engine
     *
     * @param key key
     * @return {@link IAnalyticsEngine}
     */
    public static IAnalyticsEngine<? super EngineConfig, ? super EngineItem> getEngine(final String key) {
        return sAssist.getEngine(key);
    }

    /**
     * 设置 Engine
     *
     * @param engine {@link IAnalyticsEngine}
     * @return {@link IAnalyticsEngine}
     */
    public static IAnalyticsEngine setEngine(final IAnalyticsEngine engine) {
        return sAssist.setEngine(engine);
    }

    /**
     * 设置 Engine
     *
     * @param key    key
     * @param engine {@link IAnalyticsEngine}
     * @return {@link IAnalyticsEngine}
     */
    public static IAnalyticsEngine setEngine(
            final String key,
            final IAnalyticsEngine engine
    ) {
        return sAssist.setEngine(key, engine);
    }

    /**
     * 移除 Engine
     */
    public static void removeEngine() {
        sAssist.removeEngine();
    }

    /**
     * 移除 Engine
     *
     * @param key key
     */
    public static void removeEngine(final String key) {
        sAssist.removeEngine(key);
    }

    // ==========
    // = 其他方法 =
    // ==========

    /**
     * 获取 DevEngine Generic Assist
     *
     * @return DevEngine Generic Assist
     */
    public static DevEngineAssist<IAnalyticsEngine<? super EngineConfig, ? super EngineItem>> getAssist() {
        return sAssist;
    }

    /**
     * 获取 Engine Map
     *
     * @return Engine Map
     */
    public static Map<String, IAnalyticsEngine<? super EngineConfig, ? super EngineItem>> getEngineMaps() {
        return sAssist.getEngineMaps();
    }

    /**
     * 是否存在 Engine
     *
     * @return {@code true} yes, {@code false} no
     */
    public static boolean contains() {
        return sAssist.contains();
    }

    /**
     * 是否存在 Engine
     *
     * @param key key
     * @return {@code true} yes, {@code false} no
     */
    public static boolean contains(final String key) {
        return sAssist.contains(key);
    }

    /**
     * 判断 Engine 是否为 null
     *
     * @return {@code true} yes, {@code false} no
     */
    public static boolean isEmpty() {
        return sAssist.isEmpty();
    }

    /**
     * 判断 Engine 是否为 null
     *
     * @param key key
     * @return {@code true} yes, {@code false} no
     */
    public static boolean isEmpty(final String key) {
        return sAssist.isEmpty(key);
    }
}