import React from 'react'
import paging from 'common/paging'
import Tool from 'utils/tool'

/**
 * 分页组件
 * 使用步骤：
 * 1、继承PageComponent
 * export default class ApplyDynamicApp extends PageComponent
 * 加载完成的数据保存在this.state.dataSource中
 * 2、componentDidMount 中调用fetchData加载列表数据
 * componentDidMount(){
 * 		let page = this.props.match.params.page || 1;
 * 		this.fetchData( `${ config.ajaxPath }/flyplans`, { page, size : this.params.pageSize } );
 * }
 * 3、使用filter过滤结果集
 * filter( json ){
 * 		return json.data.content;
 * }
 * 4、使用MoreBar组件 操作分页数据加载
 *  <MoreBar loadMore = { this.fetchDataByPaging } paging = { this.params }/>
 */
export default class PageComponent extends React.Component{
	constructor(props) {
		super(props);
		this.state = {
			dataSource : [],
		};
		this.params = Object.assign( {}, paging, { page : paging.currentPage, size : paging.pageSize } );
		this.fetchDataByPaging = ( currentPage ) => {
			this.fetchData( this.pagingUrl, Object.assign( {}, this.prarms, { page : currentPage } ), true );
		}
		this.fetchData = ( url, params, loadMore ) => {
			this.pagingUrl = url;
			Tool.get( this.pagingUrl ,params )
			.then( json => {
				let {
					pageNumber,
					pageSize,
					totalPages,
				} = json.data;
				Object.assign( this.params, params, {
					page : pageNumber,
					currentPage : pageNumber,
					size : pageSize,
					totalPages,
					pageSize,
				})
				return this.filter( json )
			})
			.then( dataSource => {
				this.setState( Object.assign( {}, this.state, { 
					dataSource : loadMore ? this.state.dataSource.concat( dataSource ) : dataSource
				}));
			})
		}
		
	}
	filter( json ){
		return json.data.content;
	}
}