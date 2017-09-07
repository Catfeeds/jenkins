import React from 'react'
import ListGroup from './list-group'
import Tool from 'utils/tool'
import MoreBar from 'common/more-bar'
import PageComponent from 'common/page-component'
import NoData from 'scripts/nodata'
import isEmpty from 'lodash/isEmpty'
import "styles/dynamic/implement.less"

export default class ImplementDynamicApp extends PageComponent{
	constructor(props) {
		super(props);
		this.state = {};
	}
	/**
	 * 查看凭证
	 * @param  {[type]} v [description]
	 * @return {[type]}   [description]
	 */
	checkVoucher( code ){
		Tool.to(`/dynamic/check-code/${ code }`)
	}
	/**
	 * 开始任务
	 * @param  {[type]} code  [description]
	 * @param  {[type]} index [description]
	 * @return {[type]}       [description]
	 */
	startTask( code, index ){
		Tool.fetch("PUT", `${ config.ajaxPath }/flyplan/${code}/status/start`)
		.then( json => {
			let dataSource = this.state.dataSource.slice();
			dataSource[ index ] = Object.assign( {}, dataSource[ index ], { status : 3 })
			this.setState(Object.assign({}, this.state, { dataSource }))
		})
	}
	/**
	 * 结束任务
	 * @param  {[type]} code  [description]
	 * @param  {[type]} index [description]
	 * @return {[type]}       [description]
	 */
	stopTask( code, index ){
		Tool.fetch("PUT", `${ config.ajaxPath }/flyplan/${code}/status/stop`)
		.then( json => {
			let dataSource = this.state.dataSource.slice();
			dataSource[ index ] = Object.assign( {}, dataSource[ index ], { status : 4 })
			this.setState(Object.assign({}, this.state, { dataSource }))
		})
	}
	componentDidMount(){
		let page = this.props.match.params.page || 1;
		this.fetchData( `${ config.ajaxPath }/flyplans/active`, { page, size : this.params.pageSize } );
	}
	filter( json ){
		let _l,
			dataSource = [],
			locationStr;
		Array.isArray( json.data.content ) && json.data.content.map( ( v, k ) => {
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
				time : `${ v.beginTime.replace(/-/g,'') }~${ v.endTime.split(/\s/)[1] }`,
				// time : `${ v.beginTime.replace(/-| /g,'').substr(0,10) }~${ v.endTime.replace(/-| /g,'').substr(0,10) }`,
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
			} = this.state,
			buttons = [
				{
					fn : v =>{
						switch( v.status ){
							case 1:
							case 3:
							case 4:
							return {
								text : "查看凭证",
								plain : true,
								onClick : v => this.checkVoucher( v.no )
							}
							default:
								return null;
						}
					}
				},
				{
					fn : v => {
						let button = {};
						switch( v.status ){
							case 1:
								button.text = "任务开始";
								button.type = "primary";
								button.onClick = ( v, k )=> this.startTask( v.no, k );
								break;
							case 3:
								button.text = "任务结束";
								button.type = "warn";
								button.onClick = ( v, k )=> this.stopTask( v.no, k );
								break;
							default:
								button = null;
								break;
						}
						return button;
					}
				}
			];
		
		return isEmpty( dataSource ) ? <NoData/> :(
			<div id = "implement_dynamic_app">
				{
					Array.isArray( dataSource ) && dataSource.map( ( v, k ) => <ListGroup onClick = { () => this.handleClick( v.no ) } buttons = { buttons } enkey = { k } data = { v } key = { `list_group_${k}` }/>)
				}
				<MoreBar loadMore = { this.fetchDataByPaging } paging = { this.params }/>
			</div>
		)
	}
}