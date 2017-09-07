import React from 'react'
import PageComponent from 'common/page-component'
import paging from 'common/paging'
import MoreBar from 'common/more-bar'
import NoData from 'scripts/nodata'
import isEmpty from 'lodash/isEmpty'
import { Panel, PanelHeader, PanelBody, MediaBox, MediaBoxBody, MediaBoxDescription, Icon } from 'react-weui'
import "styles/user/message.less"

export default class NoticeApp extends PageComponent{
	constructor(props) {
		super(props);
		this.state = {
			dataSource : []
		};
	}
	componentDidMount(){
		this.fetchData(`${ config.ajaxPath }/notices`, { page : 1, size : paging.pageSize } )
	}
	render(){
		let {
			dataSource
		} = this.state;
		return isEmpty( dataSource ) ? <NoData/> :(
			<div id= "notice_app">
				{
					Array.isArray( dataSource ) && dataSource.map( ( v, k ) => {
						return (
							<Panel>
								<PanelHeader><Icon value = "message" size = "small"/><span>{ v.title }</span></PanelHeader>
								<PanelBody>
									<MediaBox>
										<MediaBoxBody>{ v.content }</MediaBoxBody>
										<MediaBoxDescription>日期：{ v.createTime }</MediaBoxDescription>
									</MediaBox>
								</PanelBody>
							</Panel>
						)
					})
				}
				<MoreBar loadMore = { this.fetchDataByPaging } paging = { this.params }/>
			</div>
		)
	}
}