# 2.1.0

- Add Minecraft 1.21 support
- Migrate gradle to kotlin dsl
- Update stonecutter to 0.4

# 2.0.0-beta.10

- Downgrade records in 1.19.2 and below to allow java 6 gson to serialize them
- Fix missing translation for item stack error id not found

# 2.0.0-beta.9

- Fix 1.19.2 on dedicated servers

# 2.0.0-beta.8

- Recompile of .7 as published versions had corruption ... I have no idea how

# 2.0.0-beta.7

- Add 1.19.1-1.19.2 jar
- Fix 1.19.1/2 compat

# 2.0.0-beta.6

- Fix minecraft version deps on published builds

# 2.0.0-beta.5

- Migrate to custom_data component for persistence on item stacks

# 2.0.0-beta.4

- Add Russion Translations (mpustovoi)
- Actually fix the crash on dedicated servers

# 2.0.0-beta.3

- Fix crash on dedicated servers

# 2.0.0-beta.2

- Fix Command Permissions

# 2.0.0
### Changes
- Added NeoForge support
- New ticking suspension system:
  - Entities, block entities, block states and item stacks will no longer be killed or removed but will instead be 
    suspended until an operator takes action.
  - This should mitigate the undesired outcome where an important or useful entity is killed.
- Added new actions to the ticking entity broadcast
  - `What Is This?`: Opens the Neruina wiki page on what Neruina is and what it does
  - `Copy Crash`: Copies the cause of the ticking exception to your clipboard
  - `Teleport`: Teleports you to the location of the ticking entity
  - `Try Resume`: Attempts to resume the ticking of the ticking entity
  - `Kill`: Immediately kills and removes the ticking entity
  - `Report` (1.19+): Opens a new issue on the [NeruinaAutoReports](https://github.com/Bawnorton/NeruinaAutoReports) 
    GitHub repository and any mods that opt-in to the reporting system
- Persitance:
  - Ticking entity tracking will now persist across server restarts 
  - When the world is first started Neruina will broadcast all tracked ticking entities that need addressing
- Ticking Threshold:
  - When a certain number of ticking excpetions occur within a certain time frame, Neruina will deliberately crash in 
    order to prevent the server from becoming unusable.
  - A comprehensive report will be generated with every ticking exception that occurred with instructions on what to do next.
  - The default threshold is 10 exceptions within 5 minutes, this can be changed in the config.
- Improved performance and memory usage by delegating the ticking state to the ticking entity
- Migrated to Stonecutter to ease multi-loader multi-version development

### Auto Reports
- Added a new system for mods to opt-in to the auto reporting system
- See the schema [here](https://github.com/Bawnorton/Neruina/wiki/Auto-Report-Schema)

### Commands
- These are designed to be used internally by Neruina as it is a server-side mod and allows the client messages to 
  interact with the mod, but they are available to operators as well.
- `/neruina resume <entity|pos|player>`
  - `entity`: Resumes the ticking of the specified entity
  - `block_entity`: Resumes the ticking of the block entity at the specified position
  - `block_state`: Resumes the ticking of the block state at the specified position
  - `item_stack`: Resumes the ticking of the held item of the specified player
- `/neruina kill <entity>`: Immediately kills and removes the specified entity
- `/neruina id <entity|pos>`
  - `entity`: Returns the report UUID of the specified entity if it is being tracked 
  - `pos`: Returns the report UUID of the block entity at the specified position if it is being tracked
- `/neruina info <uuid>`: Sends the error message of the specified report UUID to the player
- `/neruina clear_tracked`: Clears all tracked ticking entities
- `/neruina show_suspended`: Displays all suspended ticking entities
- `/neruina report <uuid>` (1.19+): Automatically generates a report for the specified ticking entity via it's 
  tracked report UUID
- `/neruina cancel_login` (1.19+): Cancels the current in-progress github authentication process

### Config
- New `log_level` option that replaces `broadcast_errors`
  - `operators` (default) - Only operators will receive the broadcast
  - `everyone` - Everyone will receive the broadcast
  - `disabled` - No one will receive the broadcast
- New `ticking_exception_threshold`
  - The number of ticking exceptions that can occur within the specified time frame before Neruina will deliberately 
    crash
  - Default is `10`
  - `-1` will disable the threshold
- New `auto_kill_ticking_entities`
  - If true, ticking entities will be immediately killed and removed rather than suspended
  - Default is `false`

### Fixes
- Fixed a crash with newer versions of Forge
- Fixed a crash with does potato tick 1.18.2
