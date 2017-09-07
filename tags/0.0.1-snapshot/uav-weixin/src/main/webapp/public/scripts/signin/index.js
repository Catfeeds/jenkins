import React from 'react'
import VCButton from 'common/vc-button'
import { isEmpty } from 'lodash/isEmpty'
import { Panel, PanelBody, MediaBox, ButtonArea, Button, Cells, FormCell, CellHeader, CellBody, CellFooter, Label, Input } from 'react-weui'
import Tool from 'utils/tool'
import "styles/login.less"

export default class SigninApp extends React.Component{
	constructor(props) {
		super(props);
		this.state = {
			phone : "",
			vericode : ""
		};
	}
	componentDidMount(){
		let code = Tool.getQueryString('code');
		if (code) {
			Tool.get(`${ config.ajaxPath }/auth/autologin`, { code })
			.then( json => {
				if( json.data || !isEmpty( json.data ) ){
					Tool.saveLocalData( "__user", json.data )
					Tool.to( Tool.getLocalData("signinBeforeUrl") || "/user/center" )
				}else this.props.history.replace('/signin');
			})
			.catch( e => {
            	this.props.history.replace('/signin');
			})
		}
	}
	signin( phone, code ){
		let message = (function(){
			if( !/^\d{11}$/.test(phone) ) return "手机号码输入错误"
			if( !/^\d{6}$/.test(code) ) return "验证码输入错误"
		})()
		if( !!message ) return Tool.alert( message )
		Tool.post(`${ config.ajaxPath }/auth/login`,{ phone, code })
		.then( json => {
			if( json.data || !isEmpty( json.data ) ){
				Tool.saveLocalData( "__user", json.data )
				Tool.to( Tool.getLocalData("signinBeforeUrl") || "/user/center" )
			}
		})
	}
	changeHandle( name, e ){
        let value = !!e && e.target ? e.target.value : e;
        this.setState( Object.assign({}, this.state, { [name] : value } ) )
    }
	redirectSignup(){
		// Tool.to('/signup/1')
		Tool.fetchCodeTo("/signup/1")
		// window.location.href = `https://open.weixin.qq.com/connect/oauth2/authorize?appid=${ config.appid }&redirect_uri=${ config.serverPath }${ config.basePath }/signup/1/&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect`;
	}
	render(){
		let {
			phone,
			vericode,
		} = this.state;

		return(
			<div id="login_app">
				<Panel>
					<PanelBody>
						<MediaBox>
							发现这是您首次登录e飞，请输入登陆信息
						</MediaBox>
					</PanelBody>
				</Panel>
				<Cells>
					<FormCell vcode>
						<CellHeader><Label>手机号</Label></CellHeader>
						<CellBody><Input maxLength = { 11 } value = { phone } onChange = { e => this.changeHandle( "phone", e ) } type = "text"/></CellBody>
						<CellFooter><VCButton signup phone = { phone }/></CellFooter>
					</FormCell>
					<FormCell>
						<CellHeader><Label>验证码</Label></CellHeader>
						<CellBody><Input maxLength = { 6 } value = { vericode } onChange = { e => this.changeHandle( "vericode", e ) } type = "text"/></CellBody>
					</FormCell>
				</Cells>
				<ButtonArea>
					<Button onClick ={ () => this.signin( phone, vericode ) }>登陆</Button>
					<Button onClick = { this.redirectSignup } type = "default" plain>没有账号？马上注册</Button>
				</ButtonArea>
			</div>
		)
	}
}