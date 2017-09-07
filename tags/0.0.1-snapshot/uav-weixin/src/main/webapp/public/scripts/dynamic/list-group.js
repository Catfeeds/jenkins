import React from 'react'
import { dynamic_status, dynamic_type } from 'common/constant'
import { Panel, PanelHeader, PanelBody, PanelFooter, MediaBox, MediaBoxTitle, Cell, CellHeader, CellBody, Label, ButtonArea, Button, Flex, FlexItem } from 'react-weui'
import "styles/dynamic/list-group.less"

export default class _ListGroup extends React.Component{
	render(){
		let {
			data,
			buttons,
			enkey,
			...others
		} = this.props,
		status = dynamic_status[ data.status ],
		type = dynamic_type[ data.type ];
		return (
			<Panel className = "list_group">
				<PanelHeader><span className = { status.style }>{ data.no }</span><span className = { status.style }>{ status.text }</span></PanelHeader>
				<PanelBody>
					<MediaBox { ...others }>
						<Cell className = "list_header">
							<CellHeader><Label><span className = { `i ${ type.style }` }>{ type.text }</span></Label></CellHeader>
							<CellBody>{ data.location }</CellBody>
						</Cell>
						<Cell>
							<CellHeader><Label>飞行时间</Label></CellHeader>
							<CellBody>{ data.time }</CellBody>
						</Cell>
						<Cell>
							<CellHeader><Label>真高</Label></CellHeader>
							<CellBody>{ data.height }</CellBody>
						</Cell>
						<Cell>
							<CellHeader><Label>计划类型</Label></CellHeader>
							<CellBody>{ data.projectType }</CellBody>
						</Cell>
						<Cell>
							<CellHeader><Label>计划机型</Label></CellHeader>
							<CellBody>{ data.models }</CellBody>
						</Cell>
						{
							data.startTime && <Cell>
												<CellHeader><Label>任务开始时间</Label></CellHeader>
												<CellBody>{ data.startTime }</CellBody>
											</Cell>
						}
						{
							data.finishTime && <Cell>
												<CellHeader><Label>任务结束时间</Label></CellHeader>
												<CellBody>{ data.finishTime }</CellBody>
											</Cell>
						}
					</MediaBox>
				</PanelBody>
				<PanelFooter>
					<Flex>
						{
							Array.isArray( buttons ) && buttons.map( ( button, k ) => {
								let _button = typeof button.fn === "function" ? button.fn( data ) : button;
								if( !!!_button )
									return;
								return (
									<FlexItem key = { `btn_${ k }`}>
										<Button plain = { _button.plain } onClick = { () => _button.onClick( data, enkey ) } type = { _button.type || "default" }>{ _button.text }</Button>
									</FlexItem>
								)
							})
						}
					</Flex>
				</PanelFooter>
			</Panel>
		)
	}
}