/**
 * Vuex action
 */

import urls from '../components/urls'

export default {

    /**
     * 初始化空域数据
     * @param context
     */
    initAirSpaces(context){
        $.ajax({
            url: urls.METADATA_AIRSPACE,
            method: 'get',
            dataType: 'json',
            success: function(res) {
                context.commit('INITIALIZE_AIRSPACE',res.data);             
            },
            error: function() {
                console.log('获取空域数据失败')
            },
            global: false
        })
    },

    /**
     *  初始化起降场数据
     * @param context
     */
    initLandings(context){
        $.ajax({
            url: urls.METADATA_LANDINGS,
            method: 'get',
            dataType: 'json',
            success: function(res) {
                context.commit('INITIALIZE_LANDING',res.data);
            },
            error: function() {
                console.log('获取起降机场数据失败')
            },
            global: false
        })
    }

}