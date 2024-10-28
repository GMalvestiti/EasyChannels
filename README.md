<img alt="EasyChannels mod icon" src="src/main/resources/assets/easychannels/icon.png" style="width: 25%; height: 25%; margin-top: 1rem;">

<h1 style="padding-top: 0; margin-top: 1rem">EasyChannels</h1>



EasyChannels is a Minecraft mod that allows players to create and manage custom chat channels. This mod provides various configuration options to customize the behavior and appearance of chat messages.

### Compatibility:

- **Required**
  - [Fabric API](https://modrinth.com/mod/fabric-api)
- **Embedded**
  - [Text Placeholder API](https://modrinth.com/mod/placeholder-api)
  - [Fabric Permissions API](https://modrinth.com/mod/fabric-permissions-api)
- **Made to Work With**
  - [Styled Chat](https://modrinth.com/mod/styled-chat)
    - If the local channel is enabled, only the style format for the regular chat provided by Styled Chat will be overwritten.
  - [LuckPerms](https://modrinth.com/plugin/luckperms)
    - If you want to use LuckPerms and Styled Chat, you'll need to install the LuckPerms Placeholder API Hook mod.
      - [Offical Download](https://luckperms.net/download) for latest release
      - [My Fork](https://github.com/GMalvestiti/placeholders) for 1.20.1

### Documentation:

<details>
<summary>General Settings</summary>

- **enabled**  
  Enables or disables the mod.
    - Type: `boolean`
    - Default: `true`

- **command_argument_name**  
  The name of the command argument used for messages.
    - Type: `string`
    - Default: `"message"`

- **permissions_required_message**  
  The message displayed when a player lacks the required permissions to use a chat channel.
    - Type: `string`
    - Default: `"<red>You don't have the required permissions to use this chat channel."`
    - Local Variables: `${player}` and `${message}`
</details>

<details>
<summary>Local Channel</summary>

- **enabled**  
  Enables or disables the local chat channel.
    - Type: `boolean`
    - Default: `true`

- **format**  
  The format of the chat messages in the local channel.
    - Type: `string`
    - Default: `"<white><bold>[L]</bold></white> <gold>${player}</gold> <gray>>></gray> <white>${message}"`
    - Local Variables: `${player}` and `${message}`

- **radius**  
  The radius within which messages are visible. Set to `-1` to disable the radius.
    - Type: `int`
    - Default: `50`

- **dimension_only**  
  Restricts messages to the same dimension. If radius is enabled, this setting is ignored.
    - Type: `boolean`
    - Default: `true`
</details>

<details>
<summary>Custom Channels</summary>

- **enabled**  
  Enables or disables the channel.
    - Type: `boolean`
    - Default: `true`

- **literal**  
  The command literal used to access the channel.
    - Type: `string`

- **format**  
  The format of the chat messages.
    - Type: `string`
    - Default: `"<gold>${player}</gold> <gray>>></gray> <yellow>${message}"`
    - Local Variables: `${player}` and `${message}`

- **radius**  
  The radius within which messages are visible. Set to `-1` to disable the radius.
    - Type: `int`
    - Default: `-1`

- **dimension_only**  
  Restricts messages to the same dimension. If radius is enabled, this setting is ignored.
    - Type: `boolean`
    - Default: `false`
</details>

<details>
<summary>Permissions</summary>

Both local and custom channels allow permission settings.

- **permission_sender**  
  Defines the sender's permission requirements.
  - **permission**: Permission node to send messages (default: `null`)
  - **operator_level**: Operator level to send messages (default: `0`)

- **permission_receiver**  
  Defines the receiver's permission requirements.
  - **permission**: Permission node to receive messages (default: `null`)
  - **operator_level**: Operator level to receive messages (default: `0`)

By default the channels do not require any permissions to send or receive messages. If you add a permission check, either for the sender or receiver, the operator level is always required. A permission manager like LuckPerms is necessary to use the permission nodes.

**Permission Example**:
```json
  "permission_sender": {
    "permission": "easychannels.send",
    "operator_level": 4
  },
  "permission_receiver": {
    "permission": "easychannels.receive",
    "operator_level": 0
  }
```

</details>

<details>
<summary>Example</summary>

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
```
</details>