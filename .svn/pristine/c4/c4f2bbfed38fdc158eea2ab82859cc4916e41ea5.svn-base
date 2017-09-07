import Vue from 'vue'
import Vuex from 'vuex'
import urls from '../components/urls'
import mutations from './mutations'
import actions from './action'
import session from './modules/session'

Vue.use(Vuex)

const state = {
    //登录用户信息，没有登录的用户此对象为null
    loginUser: null,
    //证书类型
    certTypes:[{
        value:0,
        label: '未取得无人机相关证件'
    },{
        value:1,
        label: '民用无人驾驶航空器系统驾驶员合格证'
    },{
        value:2,
        label: '民用无人驾驶航空器系统操作手合格证'
    },{
        value:3,
        label: '无人机专业技能证'
    },{
        value:4,
        label: '无人机操作资格证'
    },{
        value:5,
        label: 'ASFC证书'
    }],
    //飞行计划类型
    planTypes: {
        airapply: [{
            label: '航拍摄影'
        }, {
            label: '航空测绘'
        },{
            label: '电力巡查'
        }, {
            label: '农业植保'
        }, {
            label: '快递物流'
        },{
            label: '无人机培训'
        }, {
            label: '其他'
        }],
        plan0: [{
            label: '航拍摄影'
        }, {
            label: '航空测绘'
        },{
            label: '电力巡查'
        }, {
            label: '农业植保'
        }, {
            label: '快递物流'
        },{
            label: '无人机培训'
        },{
            label: '气象监测'
        },{ label: '其他' }],
        plan1: [{
            label: '技能培训'
        }, {
            label: '产品试飞'
        }, { label: '其他' }],
        plan2: [{
            label: '娱乐飞行'
        }, { label: '其他' }]
    },
    //机型
    planeTypes: [{
        label: '多旋翼'
    }, {
        label: '混合翼'
    }, {
        label: '固定翼'
    }, {
        label: '直升机'
    }, { label: '其他' }],

    //证件类型
    idCardTypes: [{
        label:'身份证'
    },{
        label:'军官证'
    },{
        label:'护照'
    },{
        label:'机动车驾驶证'
    }],

    //飞行时间段
    flyHours:[{
        label:'06'},{
        label:'07'},{
        label:'08'},{
        label:'09'},{
        label:'10'},{
        label:'11'},{
        label:'12'},{
        label:'13'},{
        label:'14'},{
        label:'15'},{
        label:'16'},{
        label:'17'},{
        label:'18'},{
        label:'19'},{
        label:'20'},{
        label:'21'}],

    //飞行时间段-分钟
    flyMinutes:[{
        label:'00'},{
        label:'10'},{
        label:'20'},{
        label:'30'},{
        label:'40'},{
        label:'50'}],

    //空域
    airspaces: [],

    //机降场
    landings: [],
    //轮播图
    banners: [],
    //飞行计划列表
    flyplans:[],

    setFlyplans: function(flyplans){
       this.flyplans = flyplans
    },
    getFlyplan: function(code){
        return this.flyplans.filter(function(value){
            return value.number == code;
        });
    }
}

var store = new Vuex.Store({
    state,
    mutations,
    actions,
    modules: {
        session: session
    }
});

var init = function() {
    $.ajax({
        type: 'GET',
        url: urls.GET_BANNERS,
        success: function(data) {
            store.state.banners = data.data;
        },
        error: function() {
            console.log('获取轮播图数据失败');
        },
        global: false

    });
}

init();

export default store