Ext.define('Rich.model.Org',{
    extend: 'Ext.data.TreeModel',
    idProperty: 'orgId',
    parentIdProperty:'parentId',
    fields:[{
		name: "parentId",
		type:'string'
	},{
		name:'typeName',
		type:'string'
	},{
		name:'id',
		type:'int'
	},{
		name: "orgName",
		type:'string'
	},{
		name:"name",
		tyoe:'string'
	},{
		name:"type",
		tyoe:'string'
	},{
		name:"id",
		tyoe:'string'
	}]
});
