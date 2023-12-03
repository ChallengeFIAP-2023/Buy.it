import styled from "styled-components/native";

export const Container = styled.View`
  padding-top: 20px;

  width: 100%;
  height: 100%;

  background-color: ${({ theme }) => theme.COLORS.GRAY_700};
`;

export const Fieldset = styled.View`
  width: 100%;

  margin-bottom: 25px;
`;
