<template>
	<div>
    	<router-view></router-view>
    </div>
</template>

<script>


  import { Message } from 'element-ui';
  import 'element-ui/lib/theme-default/index.css'
  import $ from 'jquery'

  	export default {
        created(){
            //注册全局错误处理器
            $( document ).ajaxError(function( event, xhr, settings ) {
                try {
                    var resp = xhr.responseText;
                    var errorObject = JSON.parse(resp);
                    var message = errorObject.message;
                    Message({
                        message:message,
                        type:'error'
                    });
                    if(message=='请先登录'){
                        this.$store.dispatch('removeSession');
                        window.location.href='#login';
                    }
                }catch(e) {
                    console.error(e);
                    Message({
                        message:"无法连接服务器",
                        type:'error'
                    });
                }
            }.bind(this));
		}
  	}

</script>

<style lang="less">

</style>
