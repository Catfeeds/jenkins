Ext.BLANK_IMAGE_URL=Ext.ExtBasePath + "resources/s.gif";
Ext.PATH_APP_RESOURCES = "resources/";
Ext.CONTEXT_PATH=""
Ext.Loader.setConfig({
	enabled:true,
	disableCaching:true
});
Ext.Loader.addClassPathMappings({
	'Rich': 'src',
	/*'Ext': '../ext/src',*/
	'Ext':Ext.ExtBasePath + 'src',
	'Ext.calendar': '../extlib3/calendar/src'
});
Ext.ClassManager.addNameAlternateMappings({
 	'Rich.widget.WindowManager': ['Rich.WindowManager'],
 	'Rich.widget.Window': ['Rich.Window']
});
//添加别名，Ext.require('别名',funciton(){});
Ext.ClassManager.addNameAliasMappings({
	'Rich.widget.Window': ['widget.richwindow'],
	'Rich.widget.SectionTabpanel': ['widget.sectiontabpanel'],
	'Rich.widget.SectionWindow': ['widget.sectionwindow']
});