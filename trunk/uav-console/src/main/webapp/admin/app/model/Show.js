Ext.define('Rich.model.Show',{
    extend: 'Ext.data.Model',
    idProperty: 'id',
    fields:[
            {name:'id',type:'int'},
            'title',
            'alert'
    ]
});