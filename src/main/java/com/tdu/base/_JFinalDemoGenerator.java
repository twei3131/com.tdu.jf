package com.tdu.base;

import javax.sql.DataSource;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.plugin.druid.DruidPlugin;
import com.tdu.config.MainConfig;

public class _JFinalDemoGenerator 
{
	
	public static DataSource getDataSource() 
	{
		DruidPlugin druidPlugin = MainConfig.createDruidPlugin();
		druidPlugin.start();
		return druidPlugin.getDataSource();
	}
	
	public static void main(String[] args)
	{
		final String path = "/Users/twei3131/IdeaProjects/com.tdu.jf/";
		String baseModelPackageName = path + "com.tdu.model.base";
		String baseModelOutputDir = "src/main/java/com/tdu/model/base";
		
		String modelPackageName = "com.tdu.model";
		String modelOutputDir = baseModelOutputDir + "/..";
		
		Generator generator = new Generator(getDataSource(), baseModelPackageName, baseModelOutputDir, modelPackageName, modelOutputDir);
		generator.setDialect(new MysqlDialect());
		generator.setGenerateChainSetter(true);
		generator.addExcludedTable("adv");
		generator.setGenerateDaoInModel(true);
		generator.setGenerateDataDictionary(true);
		generator.setRemovedTableNamePrefixes("t_");
		generator.generate();
	}
}




