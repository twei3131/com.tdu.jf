package com.tdu.config;

import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.redis.RedisPlugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import com.jfinal.plugin.druid.DruidPlugin;
import com.tdu.controller.UserController;
import com.tdu.model._MappingKit;

public class MainConfig extends JFinalConfig
{
    static Prop p;
    static void loadConfig()
    {
        if (p == null)
            p = PropKit.use("config.txt");
    }

    @Override
    public void configConstant(Constants me)
    {
        loadConfig();
        me.setDevMode(true);
        me.setBaseUploadPath(PathKit.getWebRootPath() + "/upload");
        me.setMaxPostSize(2147483647);
        me.setViewType(ViewType.JSP);
        me.setUrlParaSeparator("&");
    }

    @Override
    public void configRoute(Routes me)
    {
        me.add("/user", UserController.class,"/user");
    }

    @Override
    public void configEngine(Engine me)
    {

    }

    @Override
    public void configPlugin(Plugins me)
    {
        DruidPlugin druidPlugin = new DruidPlugin(p.get("jdbcUrl"), p.get("user"), p.get("password").trim());
        me.add(druidPlugin);
        ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
        _MappingKit.mapping(arp);
        me.add(arp);
        RedisPlugin redis = new RedisPlugin("rcache", "localhost");
        me.add(redis);
    }

    public static DruidPlugin createDruidPlugin()
    {
        loadConfig();
        return new DruidPlugin(p.get("jdbcUrl"), p.get("user"), p.get("password").trim());
    }

    @Override
    public void configInterceptor(Interceptors me)
    {

    }

    @Override
    public void configHandler(Handlers me)
    {

    }

    public void afterJFinalStart()
    {
        System.out.println(PathKit.getWebRootPath());
    }
}
