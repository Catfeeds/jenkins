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
					title = "您的在线留言已成功提交"
					description = "我们客服将在7个工作日内及时回复您，请留意您的邮箱和手机信息，感谢您的关注"
					buttons = { [ { type : "primary", label : "确定", onClick : () => this.nextHandle() } ] }/>
			</div>
		)
	}
}