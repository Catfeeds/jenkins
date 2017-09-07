import React from 'react'
import Tool from 'utils/tool'
import ListGroup from './list-group'
import MoreBar from 'common/more-bar'
import PageComponent from 'common/page-component'
import NoData from 'scripts/nodata'
import isEmpty from 'lodash/isEmpty'
import "styles/dynamic/apply.less"

export default class ApplyDynamicApp extends PageComponent{
	constructor(props) {
		super(props);
	}
	componentDidMount(){
		let page = this.props.match.params.page || 1;
		this.fetchData( `${ config.ajaxPath }/flyplans/apply`, { page, size : this.params.pageSize } );
	}
	filter( json ){
		let _l,
			dataSource = [],
			locationStr;
		Array.isArray( json.data.content ) && json.data.content.map( v => {
			_l = JSON.parse(v.airInfo)[0];
			switch( v.type ){
				case 0:
					locationStr = _l.location
				break;
				case 1:
					locationStr = _l.provinceName + _l.cityName + _l.location
				break;
				case 2:
					locationStr = _l.provinceName + _l.cityName + _l.areaName + _l.location
				break;
				default:
					locationStr = ""
			}
			dataSource.push({
				status : v.status,
				type : v.type,
				no : v.number,
				location : locationStr,
				// location : `${ _l.provinceName }${ _l.cityName }${ _l.areaName }${ _l.location }`,
				// time : `${ v.beginTime.replace(/-| /g,'').substr(0,10) }~${ v.endTime.replace(/-| /g,'').substr(0,10) }`,
				time : `${ v.beginTime.replace(/-/g,'') }~${ v.endTime.split(/\s/)[1] }`,
				height : `${ _l.high }米`,
				projectType : v.planType,
				models : JSON.parse(v.planes).map( v => `${v.name}${v.number}架` ).join('、'),
				startTime : v.startTime,
				finishTime : v.finishTime,
			});
		})
		return dataSource;
	}
	handleClick( code ){
		Tool.to(`/dynamic/detail/${ code }`)
	}
	render(){
		let {
			dataSource
		} = this.state;
		
		return isEmpty( dataSource ) ? <NoData/> : (
			<div id = "apply_dynamic_app">

				{
					Array.isArray( dataSource ) && dataSource.map( ( v, k ) => <ListGroup data = { v } onClick = { () => this.handleClick( v.no ) } key = { `list_group_${k}` }/>)
				}
				<MoreBar loadMore = { this.fetchDataByPaging } paging = { this.params }/>
			</div>
		)
	}
}