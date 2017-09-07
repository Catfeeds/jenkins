import React from 'react'
import Tool from 'utils/tool'
import Breadcrumbs from './breadcrumbs'
import Signup1App from 'scripts/signup/signup1'
import Signup2App from 'scripts/signup/signup2'
import Signup3App from 'scripts/signup/signup3'
import Signup4App from 'scripts/signup/signup4'
import "styles/signup/index.less"

let steps = ["手机验证码","设置密码","基本资料"],
	RegxOnlyNumber = /^\d{0,}$/;
export default class SignupApp extends React.Component{
	constructor(props) {
		super(props);
		
		this.state = {
			// signupType : 1,
			signupType : undefined,
			mobile : "",
			vericode : "",
			username : "",
			password1 : "",
			password2 : "",
			realname : "",
			gender : 0,
			idCard : "",
			companyName : "",
			companyCity : "",
			companyProvince : "",
			companyArea : "",
			compnayAddr : "",
			licenseCode : "",
		};
	}
	componentDidMount(){
		let code = Tool.getQueryString('code');
		if (code) {
			Tool.get(`${ config.ajaxPath }/auth/autologin`, { code })
			// .then( json => {
			// 	Tool.to( this.props.signinBeforeUrl )
			// })
		}
	}
	changeHandle( name, e, type ){
		let value,
			valid = true;
		if( !!e && !!e.target ){
			e.persist();
			value = e.target.value
		}else value = e
		switch( type ){
			case "number":
				valid = RegxOnlyNumber.test( value )
				valid && value && ( value = parseInt( value ) )
			break;
		}
		valid && this.setState( Object.assign( {}, this.state, { [name] : value } ) )
	}
	render(){
		let step = parseInt( this.props.match.params.step ) || 0,
			breadcrumbs,
			stepComponent;

		switch( step ){
			case 2:
				stepComponent = <Signup2App { ...this.state } changeHandle = { ( name, e ) => this.changeHandle( name, e ) }/>;
				breadcrumbs = <Breadcrumbs steps = { steps } step = { 1 }/>
				break;
			case 3:
				stepComponent = <Signup3App { ...this.state } changeHandle = { ( name, e ) => this.changeHandle( name, e ) }/>;
				breadcrumbs = <Breadcrumbs steps = { steps } step = { 2 }/>
				break;
			case 4:
				stepComponent = <Signup4App { ...this.state } toggleAgreement = { () => this.toggleAgreement() } changeHandle = { ( name, e ) => this.changeHandle( name, e ) }/>;
				breadcrumbs = <Breadcrumbs steps = { steps } step = { 3 }/>
				break;
			case 0:
			case 1:
			default:
				stepComponent = <Signup1App { ...this.state } changeHandle = { ( name, e ) => this.changeHandle( name, e ) }/>;
				break;
		}
		return (
			<div id="signup_app">
				{ breadcrumbs }
				{ stepComponent }
			</div>
		)
	}
}