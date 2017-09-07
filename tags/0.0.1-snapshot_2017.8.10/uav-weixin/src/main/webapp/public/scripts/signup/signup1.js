import React from 'react'
import { ButtonArea, Button } from 'react-weui'
import Tool from 'utils/tool'

export default class Signup1App extends React.Component{
	redirectFillInfo( type ){
		let {
			changeHandle,
		} = this.props;
		changeHandle( "signupType", type );
		Tool.to("/signup/2");
	}
	render(){
		return (
			<div id="sign1_app">
				<ButtonArea>
					<Button onClick = { () => this.redirectFillInfo( 0 ) } type = "default" plain>个人用户</Button>
					<Button onClick = { () => this.redirectFillInfo( 1 ) } style = {{ marginTop: "30px" }} type = "default" plain>企业用户</Button>
				</ButtonArea>
			</div>
		)
	}
}