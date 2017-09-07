import React from 'react'
import Tool from 'utils/tool'
// import { Tabs, Tab } from 'react-bootstrap/lib'
import Form1 from './form1'
import Form2 from './form2'
import Form3 from './form3'
import Tab from 'common/tab'
import SwithView from 'common/tab/switch-view'
import { resetForm } from './action'
import { connect } from 'react-redux'
import "styles/apply-record.less"

class ApplyRecordApp extends React.Component{
	constructor(props) {
		super(props);
		this.state = {
			key: 0,
		}
	}
	componentDidMount(){
        Tool.get(`${ config.ajaxPath }/user/info`)
		.then( json => {
			if( json.data.authStatus !== 3 ){
	            Tool.alert(`用户未通过认证，无法使用空域申请功能` )
	            Tool.to(`/user/center`)
			}
		})
		let tabIndex = this.props.match.params.tabIndex  
		this.setState({ key : parseInt( tabIndex ) || 1 });
	}
	getToday(){
		let date = new Date(),
			month = ( month = date.getMonth() + 1 ) > 9 ? month : `0${ month }`,
			day = ( day = date.getDate() ) > 9 ? day : `0${ day }`;
		return `${ date.getFullYear() }-${ month }-${ day }`;
	}
	getTomorrow(){
		let date = new Date(),
			month,
			day;
		date.setTime( date.getTime() + 1 * 24 * 60 * 60 * 1000 )
		month = ( month = date.getMonth() + 1 ) > 9 ? month : `0${ month }`
		day = ( day = date.getDate() ) > 9 ? day : `0${ day }`
		return `${ date.getFullYear() }-${ month }-${ day }`;
	}
	handleSelect(key) {
		// this.setState({key});
		Tool.to(`/apply-record/${key}`)
		if( key === 3 ) this.props.dispatch( resetForm() )
		this.setState({ key });
	}
	render(){
		let {
			key
		} = this.state;
		return (
			<div id="apply-record-tab">
				<SwithView>
					<Tab active = { key === 1 } handlerActive = { () => this.handleSelect(1) } label = "一类报告空域"><Form1 getTomorrow = { this.getTomorrow } { ...this.state }/></Tab>
					<Tab active = { key === 2 } handlerActive = { () => this.handleSelect(2) } label = "二类报告空域"><Form2 getToday = { this.getToday } { ...this.state }/></Tab>
					<Tab active = { key === 3 } handlerActive = { () => this.handleSelect(3) } label = "管制空域"><Form3 getTomorrow = { this.getTomorrow } { ...this.state }/></Tab>
				</SwithView>
			</div>
		)
	}
}

export default connect()(ApplyRecordApp)