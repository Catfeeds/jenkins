export const dynamic_status = [{
    text: "审核中",
    style: "blue",
}, {
    text: "已审核",
    style: "green",
}, {
    text: "审核未通过",
    style: "black",
}, {
    text: "已开始",
    style: "blue",
}, {
    text: "已完成",
    style: "black",
}, {
    text: "已超时",
    style: "red",
}, {
    text: "已撤销",
    style: "red",
}, {
    text: "已关闭",
    style: "black",
}]

export const dynamic_type = [{
    text: "管制",
    style: "red-bg",
}, {
    text: "Ⅰ类",
    style: "green-bg",
}, {
    text: "Ⅱ类",
    style: "green-bg",
}, ]

export const card_type = [{
    label: "身份证",
    value: 0,
}, {
    label: "军官证",
    value: 1,
}, {
    label: "护照",
    value: 2,
}, {
    label: "驾驶证",
    value: 3,
}]
export const auth_status = {
    1: "未认证",
    2: "待认证",
    3: "已认证",
    4: "认证失败"
}
export const planeTypes = [{
    name: '多旋翼',
}, {
    name: '混合翼',
}, {
    name: '固定翼',
}, {
    name: '直升机',
}, {
    name: '其他',
}, ]

export const projectTypes = [
    // 管制空域
    [{
        label: '航拍摄影',
        value: 0,
    }, {
        label: '航空测绘',
        value: 1,
    }, {
        label: '电力巡查',
        value: 2,
    }, {
        label: '农业植保',
        value: 3,
    }, {
        label: '快递物流',
        value: 4,
    }, {
        label: '无人机培训',
        value: 5,
    }, {
        label: '气象监测',
        value: 6,
    }, {
        label: '其他',
        value: 7,
    }],
    // 一类
    [{
        label: '技能培训',
        value: 0,
    }, {
        label: '产品试飞',
        value: 1,
    }, {
        label: '其他',
        value: 2,
    }],
    // 二类
    [{
        label: '娱乐飞行',
        value: 0,
    }, {
        label: '其他',
        value: 1,
    }, ],
    // 空域申请
    [{
        label: '航拍摄影',
        value: 0,
    }, {
        label: '航空测绘',
        value: 1,
    }, {
        label: '电力巡查',
        value: 2,
    }, {
        label: '农业植保',
        value: 3,
    }, {
        label: '快递物流',
        value: 4,
    }, {
        label: '无人机培训',
        value: 5,
    }, {
        label: '其他',
        value: 6,
    }, ]
]

export const timeList = (function() {
    let minutes = ["00", "10", "20", "30", "40", "50", ].map(v => ({ name: v + "分", value: v }));
    return ["06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", ].map(v => ({ name: v + "点", value: v, items: minutes }))
})()
