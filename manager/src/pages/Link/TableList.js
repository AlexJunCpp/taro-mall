import React, { PureComponent } from 'react';
import { connect } from 'dva';
import { Card, Form } from 'antd';
import PageHeaderWrapper from '@/components/PageHeaderWrapper';
import fetchPage from '../../utils/fetchPage';

import styles from './TableList.less';

/* eslint react/no-multi-comp:0 */
@connect(({ loading }) => ({
  loading: loading.models.link,
}))
@Form.create()
class TableList extends PureComponent {
  componentDidMount() {
    fetchPage('linkLocation');
  }

  render() {
    return (
      <PageHeaderWrapper title="链接列表">
        <Card bordered={false}>
          <div className={styles.tableList}>链接列表</div>
        </Card>
      </PageHeaderWrapper>
    );
  }
}

export default TableList;
