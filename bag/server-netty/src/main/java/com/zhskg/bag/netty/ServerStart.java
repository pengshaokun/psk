package com.zhskg.bag.netty;

import com.zhskg.bag.netty.util.ConfigUtil;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

/**
 * Created by lwb on 2018/5/15.
 */
public class ServerStart {

	public static void main(String[] args) {
		System.out.println("netty-----启动");
		System.setProperty("es.set.netty.runtime.available.processors", "false");
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(workerGroup)
				.channel(NioDatagramChannel.class)// NioSocketChannel 是tcp格式 ;NioDatagramChannel UDP格式
				.option(ChannelOption.SO_BROADCAST, true)
				.handler(new ParsingHandler());

			// 绑定端口，同步等待成功
			ChannelFuture f = b.bind(ConfigUtil.Server_Port).sync();

			// 等待服务端监听端口关闭
			f.channel().closeFuture().sync();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// 优雅退出，释放线程池资源
			workerGroup.shutdownGracefully();
		}
	}
}
