# Computation
*The base Computation code and API!*

**Computation** is meant to be the base API for its computers and related code for easy expandion by the Computation team and any other third-party developers!

## API
### Miscellanious
- `BaseMachine`
  - An abstract class meant to be used for implementing different programming languages (Lua, Python, Javascript, etc)
- `Computer`
  - A component used to store which `BaseMachine` is being using for a computer
- `ComputerState`
  - A component used to store the state of a computer
- `ComputerUI`
  - A UI system for handling computer input and display.
### Systems
- `ComputerTickingSystem`
  - An abstract class meant to be used for handling new per-tick information about computers
- `ComputerRefChangeSystem`
  - An abstract class meant to be used for handling component changes for the computers
- `ComputerRefSystem`
- `ComputerHolderSystem`
- `ComputerEventSystem`
