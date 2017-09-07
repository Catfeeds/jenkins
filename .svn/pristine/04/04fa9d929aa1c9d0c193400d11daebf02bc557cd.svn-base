Ext.define('Ext.calendar.data.Events', {

    statics: {
        getData: function() {
            var today = Ext.Date.clearTime(new Date()), 
                makeDate = function(d, h, m, s) {
                    d = d * 86400;
                    h = (h || 0) * 3600;
                    m = (m || 0) * 60;
                    s = (s || 0);
                    return Ext.Date.add(today, Ext.Date.SECOND, d + h + m + s);
                };
                
            return {
                "evts": [{
                    "id": 1001,
                    "cid": 1,
                    "title": "xxxxxxxxxxxxxxxxxxxxxxx",
                    "start": makeDate(-20, 10),
                    "end": makeDate(-10, 15),
                    "notes": "Have fun777777777777777777777777777777777777777777777777"
                }, {
                    "id": 1002,
                    "cid": 2,
                    "title": "ggggggggggggggggg",
                    "start": makeDate(0, 11, 30),
                    "end": makeDate(0, 13),
                    "loc": "ttttttttttt",
                    "url": "dddddddddddd",
                    "notes": "ffffffffffffffff",
                    "rem": "15"
                }]
            }
        }
    }
});
