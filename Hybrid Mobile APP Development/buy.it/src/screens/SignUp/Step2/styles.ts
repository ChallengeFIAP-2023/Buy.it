import styled from "styled-components/native";

export const Container = styled.View`
  padding-top: 20px;

  width: 100%;
  height: 100%;

  background-color: ${({ theme }) => theme.COLORS.GRAY_700};
`;

export const Fieldset = styled.View`
  width: 100%;

  margin-bottom: 45px;
`;

export const Touchable = styled.TouchableOpacity`
  align-items: center;
  flex-direction: row;
  gap: 5px;
  justify-content: center;

  margin-top: 65px;
`;

export const WrapChip = styled.View`
  display: flex;
  flex-direction: row;
  gap: 7px;
  flex-wrap: wrap;
  margin-top: 15px;
  margin-bottom: 45px;
`;
