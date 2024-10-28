<div style="text-align: center; margin-top: 2rem;">
  <img alt="EasyChannels mod icon" src="src/main/resources/assets/easychannels/icon.png" style="width: 25%; height: 25%; margin-top: 1rem;">
  <h1 style="margin-top: 1rem; font-size: 2.5rem; color: #4A4A4A;">EasyChannels</h1>
</div>

<p style="text-align: center; font-size: 1.2rem; color: #555;">
  <em>EasyChannels</em> is a Minecraft mod enabling players to create and manage custom chat channels with extensive options for message behavior and appearance.
</p>

### Compatibility:

<ul>
  <li><strong>Required:</strong> <a href="https://modrinth.com/mod/fabric-api">Fabric API</a></li>
  <li><strong>Embedded:</strong> <a href="https://modrinth.com/mod/placeholder-api">Text Placeholder API</a>, <a href="https://modrinth.com/mod/fabric-permissions-api">Fabric Permissions API</a></li>
  <li><strong>Made to Work With:</strong>
    <ul>
      <li><a href="https://modrinth.com/mod/styled-chat">Styled Chat</a>
        <ul>
          <li>If the local channel from EasyChannels is enabled, only the style format for the regular chat provided by Styled Chat will be overwritten.</li>
        </ul>
      </li>
      <li><a href="https://modrinth.com/plugin/luckperms">LuckPerms</a>
        <ul>
          <li>If you want to use LuckPerms and Styled Chat, you'll need to install the LuckPerms Placeholder API Hook mod.</li>
            <ul>
              <li><a href="https://luckperms.net/download">Offical Download</a> for the latest minecraft version.</li>
              <li><a href="https://github.com/GMalvestiti/placeholders">EasyChannels Fork</a> for 1.20.1 only</li>
            </ul>
        </ul>
      </li>
    </ul>
  </li>
</ul>

---

### Documentation:

<details>
  <summary><strong>General Settings</strong></summary>
  <ul>
    <li><strong>enabled</strong> - Enables or disables the mod.<br><em>Type:</em> <code>boolean</code>, <em>Default:</em> <code>true</code></li>
    <li><strong>command_argument_name</strong> - Command argument name for messages.<br><em>Type:</em> <code>string</code>, <em>Default:</em> <code>"message"</code></li>
    <li><strong>permissions_required_message</strong> - Message for insufficient permissions.<br><em>Type:</em> <code>string</code>, <em>Default:</em> <code>"&lt;red&gt;You don't have the required permissions to use this chat channel."</code><br>Local Variables: <code>${player}</code>, <code>${message}</code></li>
  </ul>
</details>

<details>
  <summary><strong>Local Channel</strong></summary>
  <ul>
    <li><strong>enabled</strong> - Enables/disables the local chat channel.<br><em>Type:</em> <code>boolean</code>, <em>Default:</em> <code>true</code></li>
    <li><strong>format</strong> - Message format in local channel.<br><em>Type:</em> <code>string</code>, <em>Default:</em> <code>"&lt;white&gt;&lt;bold&gt;[L]&lt;/bold&gt;&lt;/white&gt; &lt;gold&gt;${player}&lt;/gold&gt; &lt;gray&gt;&gt;&gt;&lt;/gray&gt; &lt;white&gt;${message}"</code><br>Local Variables: <code>${player}</code>, <code>${message}</code></li>
    <li><strong>radius</strong> - Visibility radius (set <code>-1</code> to disable).<br><em>Type:</em> <code>int</code>, <em>Default:</em> <code>50</code></li>
    <li><strong>dimension_only</strong> - Restrict messages to the same dimension. Ignored if radius enabled.<br><em>Type:</em> <code>boolean</code>, <em>Default:</em> <code>true</code></li>
  </ul>
</details>

<details>
  <summary><strong>Custom Channels</strong></summary>
  <ul>
    <li><strong>enabled</strong> - Enables/disables the channel.<br><em>Type:</em> <code>boolean</code>, <em>Default:</em> <code>true</code></li>
    <li><strong>literal</strong> - Command literal for channel access.<br><em>Type:</em> <code>string</code></li>
    <li><strong>format</strong> - Custom message format.<br><em>Type:</em> <code>string</code>, <em>Default:</em> <code>"&lt;gold&gt;${player}&lt;/gold&gt; &lt;gray&gt;&gt;&gt;&lt;/gray&gt; &lt;yellow&gt;${message}"</code><br>Local Variables: <code>${player}</code>, <code>${message}</code></li>
    <li><strong>radius</strong> - Visibility radius (set <code>-1</code> to disable).<br><em>Type:</em> <code>int</code>, <em>Default:</em> <code>-1</code></li>
    <li><strong>dimension_only</strong> - Restrict messages to same dimension (ignored if radius enabled).<br><em>Type:</em> <code>boolean</code>, <em>Default:</em> <code>false</code></li>
  </ul>
</details>

<details>
  <summary><strong>Permissions</strong></summary>
  <p>Both local and custom channels allow permission settings. By default, channels do not require any permissions to send or receive messages. If you add a permission check, either for the sender or receiver, the operator level is always required. A permission manager like <a href="https://modrinth.com/plugin/luckperms">LuckPerms</a> is necessary to use permission nodes.</p>

  <ul>
    <li><strong>permission_sender</strong> - Defines the sender's permission requirements.<br>
      <code>permission:</code> Permission node to send messages (default: <code>null</code>)<br>
      <code>operator_level:</code> Operator level to send messages (default: <code>0</code>)
    </li>
    <li><strong>permission_receiver</strong> - Defines the receiver's permission requirements.<br>
      <code>permission:</code> Permission node to receive messages (default: <code>null</code>)<br>
      <code>operator_level:</code> Operator level to receive messages (default: <code>0</code>)
    </li>
  </ul>

  <p><strong>Permission Example</strong>:</p>
  <pre>
{
  "permission_sender": {
    "permission": "easychannels.send",
    "operator_level": 4
  },
  "permission_receiver": {
    "permission": "easychannels.receive",
    "operator_level": 0
  }
}
  </pre>
</details>


<details>
  <summary><strong>Full Configuration Example</strong></summary>

```json
{
  "enabled": true,
  "command_argument_name": "message",
  "permissions_required_message": "<red>You don't have the required permissions to use this chat channel.",
  "local_channel": {
    "enabled": true,
    "format": "<white><bold>[L]</bold></white> <gold>${player}</gold> <gray>>></gray> <white>${message}",
    "radius": 50,
    "dimension_only": true
  },
  "custom_channels": [
    {
      "enabled": true,
      "literal": "global",
      "format": "<gold>${player}</gold> <gray>>></gray> <yellow>${message}",
      "radius": -1,
      "dimension_only": false
    },
    {
      "enabled": true,
      "literal": "staff",
      "format": "<gold>${player}</gold> <gray>>></gray> <yellow>${message}",
      "radius": -1,
      "dimension_only": false,
      "permission_sender": {
        "permission": "easychannels.send.staff",
        "operator_level": 0
      },
      "permission_receiver": {
        "operator_level": 4
      }
    }
  ]
}
