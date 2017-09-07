/**
 * Simplified Chinese translation
 * By Ray
 * 09 April 2016
 *
 */
Ext.onReady(function() {
    var parseCodes;

    if (Ext.Date) {
       
    }
});

Ext.define("Ext.calendar.Lang-zh_CN.CalendarPanel", {
    override: "Ext.calendar.CalendarPanel",
    todayText: '今天',
    dayText: '日',
    weekText: '星期',
    monthText: '月'
});
Ext.define("Ext.calendar.Lang-zh_CN.view.Month", {
    override: "Ext.calendar.view.Month",
    todayText: '今天'
});
Ext.define("Ext.calendar.Lang-zh_CN.view.Day", {
    override: "Ext.calendar.view.Day",
    todayText: '今天'
});
Ext.define("Ext.calendar.Lang-zh_CN.view.AbstractCalendar", {
    override: "Ext.calendar.view.AbstractCalendar",
    ddCreateEventText: '创建事件到 {0}',
    ddMoveEventText: '移动事件到 {0}',
    ddResizeEventText: '更新事件到 {0}'
});

Ext.define("Ext.calendar.Lang-zh_CN.form.EventDetails", {
    override: "Ext.calendar.form.EventDetails",
    title: '事件表单',
    titleTextAdd: '添加事件',
    titleTextEdit: '编辑事件'
});
Ext.define("Ext.calendar.Lang-zh_CN.form.field.CalendarCombo", {
    override: "Ext.calendar.form.field.CalendarCombo",
    fieldLabel: '日历'
    //fieldLabel: 'Calendar',
});
Ext.define("Ext.calendar.Lang-zh_CN.form.field.DateRange", {
    override: "Ext.calendar.form.field.DateRange",
    
    toText: '到',
    allDayText: '全天'
    /*
    toText: 'to',
    allDayText: 'All day',
    dateFormat: 'n/j/Y',
    timeFormat: Ext.Date.use24HourTime ? 'G:i' : 'g:i A'
    */
});

Ext.define("Ext.calendar.Lang-zh_CN.form.field.ReminderCombo", {
    override: "Ext.calendar.form.field.ReminderCombo",
    
    fieldLabel: '提醒',
    initComponent: function() {
        this.store = this.store || new Ext.data.ArrayStore({
            fields: ['value', 'desc'],
            idIndex: 0,
            data: [
            ['', '无'],
            ['0', '在开始时间'],
            ['5', '在开始前5分钟'],
            ['15', '在开始前15分钟'],
            ['30', '在开始前30分钟'],
            ['60', '在开始前1小时'],
            ['90', '在开始前1.5小时'],
            ['120', '在开始前2小时'],
            ['180', '在开始前3小时'],
            ['360', '在开始前6小时'],
            ['720', '在开始前12小时'],
            ['1440', '在开始前1天'],
            ['2880', '在开始前2天'],
            ['4320', '在开始前3天'],
            ['5760', '在开始前4天'],
            ['7200', '在开始前5天'],
            ['10080', '在开始前1周'],
            ['20160', '在开始前2周']
            ]
        });
        this.callParent();
    }
    /*
    fieldLabel: 'Reminder',
    
    initComponent: function() {
        this.store = this.store || new Ext.data.ArrayStore({
            fields: ['value', 'desc'],
            idIndex: 0,
            data: [
            ['', 'None'],
            ['0', 'At start time'],
            ['5', '5 minutes before start'],
            ['15', '15 minutes before start'],
            ['30', '30 minutes before start'],
            ['60', '1 hour before start'],
            ['90', '1.5 hours before start'],
            ['120', '2 hours before start'],
            ['180', '3 hours before start'],
            ['360', '6 hours before start'],
            ['720', '12 hours before start'],
            ['1440', '1 day before start'],
            ['2880', '2 days before start'],
            ['4320', '3 days before start'],
            ['5760', '4 days before start'],
            ['7200', '5 days before start'],
            ['10080', '1 week before start'],
            ['20160', '2 weeks before start']
            ]
        });
        this.callParent();
    }
    */
});


Ext.define("Ext.calendar.Lang-zh_CN", {	
    override: "Ext.Component"
});

