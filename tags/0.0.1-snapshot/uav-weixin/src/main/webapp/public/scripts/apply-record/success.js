import React from 'react'
import Tool from 'utils/tool'
import { Msg } from 'react-weui'
import "styles/success.less"

export default class SuccessApp extends React.Component{
	nextHandle(){
		Tool.to('/user/center')
	}
	render(){
		return (
			<div id = "success_app">
				<Msg type = "success"
					title = "飞行计划备案提交成功"
					// description = "我们的客户将与7个工作日内联系您，请留意你的邮箱或手机"
					buttons = { [ { type : "primary", label : "确定", onClick : () => this.nextHandle() } ] }/>
			</div>
		)
	}
}