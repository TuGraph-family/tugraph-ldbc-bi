# -*- coding: utf-8 -*-
# This file is auto-generated, don't edit it. Thanks.
import os
import sys
import json

from typing import List

from alibabacloud_cs20151215.client import Client as CS20151215Client
from alibabacloud_ecs20140526.client import Client as Ecs20140526Client
from alibabacloud_tea_openapi import models as open_api_models
from alibabacloud_cs20151215 import models as cs20151215_models
from alibabacloud_ecs20140526 import models as ecs_20140526_models
from alibabacloud_tea_util import models as util_models
from alibabacloud_tea_util.client import Client as UtilClient
import oss2
from oss2.credentials import EnvironmentVariableCredentialsProvider


class AliyunClient:

    def __init__(self, access_key_id, access_key_secret):
        config1 = open_api_models.Config(
            access_key_id=access_key_id,
            access_key_secret=access_key_secret
        )
        config1.endpoint = f'cs.cn-hangzhou.aliyuncs.com'
        self.k8s_client = CS20151215Client(config1)

        config2 = open_api_models.Config(
            access_key_id=access_key_id,
            access_key_secret=access_key_secret
        )
        config2.endpoint = f'ecs.cn-hangzhou.aliyuncs.com'

        self.ecs_client = Ecs20140526Client(config2)

        auth = oss2.Auth(access_key_id, access_key_secret)
        self.bucket = oss2.Bucket(auth, 'http://oss-cn-hangzhou.aliyuncs.com', 'geaflow-ldbc')

    def get_cluster_id(self, name): 
        describe_clusters_v1request = cs20151215_models.DescribeClustersV1Request()
        runtime = util_models.RuntimeOptions()
        headers = {}
        try:
            response = self.k8s_client.describe_clusters_v1with_options(describe_clusters_v1request, headers, runtime)
            cluster_info = []
            for cluster in response.body.clusters:
                if cluster.name == name:
                    return cluster.cluster_id
            return "" 
        except Exception as error:
            UtilClient.assert_as_string(error.message)

    def save_kube_config(self, cluster_id):
        describe_cluster_user_kubeconfig_request = cs20151215_models.DescribeClusterUserKubeconfigRequest(private_ip_address=True)
        runtime = util_models.RuntimeOptions()
        headers = {}
        try:
            response = self.k8s_client.describe_cluster_user_kubeconfig_with_options(cluster_id, describe_cluster_user_kubeconfig_request, headers, runtime)
            kube_config = response.body.config
            with open('kube.yaml', 'w') as f:
                f.write(kube_config)
        except Exception as error:
            UtilClient.assert_as_string(error.message)

    def get_node_ids(self, cluster_id):
        describe_cluster_nodes_request = cs20151215_models.DescribeClusterNodesRequest(page_size='100')
        runtime = util_models.RuntimeOptions()
        headers = {}
        try:
            response = self.k8s_client.describe_cluster_nodes_with_options(cluster_id, describe_cluster_nodes_request, headers, runtime)
            ids = []
            for node in response.body.nodes:
                ids.append(node.instance_id)
            return ids
        except Exception as error:
            print(UtilClient.assert_as_string(error.message))

    def get_nodes_attr(self, node_ids) :
        describe_instances_request = ecs_20140526_models.DescribeInstancesRequest(
            region_id='cn-hangzhou',
            page_size='100',
            instance_ids=str(node_ids)
        )
        runtime = util_models.RuntimeOptions()
        try:
            response = self.ecs_client.describe_instances_with_options(describe_instances_request, runtime)
            attrs = []
            with open('cluster_nodes', 'w') as f:
                print('cluster nodes: ')
                f.write('cluster nodes: \n')
                for node in response.body.instances.instance:
                    attr = {'id': node.instance_id, 'type': node.instance_type, 'ip': node.vpc_attributes.private_ip_address.ip_address[0], 'cpu': node.cpu, 'memory': node.memory}
                    print(attr)
                    f.write(str(attr)[1:-1] + '\n')
                    attrs.append(attr)
            return attrs
        except Exception as error:
            print(UtilClient.assert_as_string(error.message))

    def get_node_disk_size(self, ecs_id):
        describe_disks_request = ecs_20140526_models.DescribeDisksRequest(
            region_id='cn-hangzhou',
            instance_id=ecs_id
        )
        runtime = util_models.RuntimeOptions()
        try:
            response = self.ecs_client.describe_disks_with_options(describe_disks_request, runtime)
            return response.body.disks.disk[0].size
        except Exception as error:
            UtilClient.assert_as_string(error.message)

    def get_master_info(self, node_attrs): 
        for attr in node_attrs:
            if attr['memory'] == 32768:
                return {'cpu': attr['cpu'], 'memory': int(attr['memory'] / 1024), 'ip': attr['ip']}

    def get_worker_info(self, node_attrs): 
        size = len(node_attrs)
        for attr in node_attrs:
            if attr['memory'] != 32768:
                disk_size = self.get_node_disk_size(attr['id'])
                return {'cpu': attr['cpu'], 'memory': int(attr['memory'] / 1024), 'disk': disk_size, 'number': size - 1}

    def get_oss_file(self, file_name): 
        self.bucket.get_object_to_file(file_name, file_name)        