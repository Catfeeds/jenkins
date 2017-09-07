import React from 'react'
import InputList from 'common/weui/input-list'
import TextArea from 'common/weui/textarea'
import Tool from 'utils/tool'
import { Panel, PanelHeader, PanelBody, Cells, CellsTitle, ButtonArea, Button } from 'react-weui'
import "styles/service/consult.less"

export default class ServiceConsultApp extends React.Component{
	constructor(props) {
		super(props);
		this.state = {
			name : "", //姓名
			email : "", //邮箱
			phone : "", //手机号码
			content : "",  //咨询内容
		};
	}
	changeHandle( name, e ){
		this.setState( Object.assign( {}, this.state, { [name] : e.target.value } ) );
	}
	submitHandle(){
		let {
			name,
			email,
		 	phone,
		 	content 
		} = this.state;
		if( !name ) return Tool.alert("请输入您的名字")
		// if( !email && !phone ) return Tool.alert("请留下一个您的联系方式吧")
		if( !!phone && !/1\d{10}/.test( phone ) ) return Tool.alert("手机号码输入错误")
		if( !/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/ .test( email ) ) return Tool.alert("邮箱格式输入错误")
		if( !content.trim() ) return Tool.alert("请输入咨询内容")
		Tool.post(`${ config.ajaxPath }/message`,{ name, email, phone,content })
		.then( json => {
			// Tool.alert("您的咨询内容已发送成功，我们的客户将与7个工作日内联系您，请留意你的邮箱或手机")
			// this.setState( Object.assign( {}, this.state, { 
			// 	name : "",
			// 	email : "",
			// 	phone : "",
			// 	content : "",
			//  } ) );
			 Tool.to(`/service/consult/success`)
		})
	}
	render(){
		let {
			name, //姓名
			email, //邮箱
			phone, //手机号码
			content,  //咨询内容
		} = this.state;
		return (
			<div id="consult_app">
				<Cells>
					<InputList value = { name } placeholder = "请输入姓名" onChange = { e => this.changeHandle( "name", e ) } label = "姓名"/>
					<InputList value = { email } placeholder = "请输入邮箱" onChange = { e => this.changeHandle( "email", e ) } label = "邮箱"/>
					<InputList value = { phone } placeholder = "请输入手机号码" onChange = { e => this.changeHandle( "phone", e ) } label = "手机号码"/>
				</Cells>
				<Panel>
					<PanelHeader style = {{ color : "#000" }}>咨询内容</PanelHeader>
					<PanelBody>
						<TextArea placeholder = "请输入留言信息，最多可输入200字" value = { content } onChange = { e => this.changeHandle( "content", e ) } style = {{ height: "70px" }} maxlength = { 200 }/>
					</PanelBody>
				</Panel>
				<CellsTitle style = {{ color : "#f76260" }}>我们客服将在7个工作日内及时回复您，请留意您的邮箱和手机信息，感谢您的关注</CellsTitle>
				<ButtonArea>
					<Button onClick = { () => this.submitHandle() }>提交</Button>
				</ButtonArea>
			</div>
		)
	}
}